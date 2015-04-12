package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.mobandme.android.transformer.Transformer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.data.repository.caching.strategy.CachingStrategy;
import me.panavtec.cleancontacts.data.repository.caching.strategy.list.ListCachingStrategy;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors.Persistor;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.DeleteContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.InvalidCacheException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.PersistContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownPersistContactsException;

public class ContactsBddDataSourceImp implements ContactsBddDataSource {

  private final Dao<BddContact, Integer> daoContacts;
  private final Persistor<BddContact> persistor;
  private final CachingStrategy<BddContact> cachingStrategy;
  private final ListCachingStrategy<BddContact> listCachingStrategy;
  private static final Transformer transformer = new Transformer.Builder().build(BddContact.class);

  public ContactsBddDataSourceImp(Persistor<BddContact> persistor,
      Dao<BddContact, Integer> daoContacts, CachingStrategy<BddContact> cachingStrategy,
      ListCachingStrategy<BddContact> listCachingStrategy) {
    this.daoContacts = daoContacts;
    this.persistor = persistor;
    this.cachingStrategy = cachingStrategy;
    this.listCachingStrategy = listCachingStrategy;
  }

  @Override public List<Contact> obtainContacts()
      throws ObtainContactsBddException, UnknownObtainContactsException, InvalidCacheException {
    try {
      List<BddContact> bddContacts = daoContacts.queryForAll();
      if (!listCachingStrategy.isValid(bddContacts)) {
        deleteBddContacts(listCachingStrategy.candidatesToPurgue(bddContacts));
        throw new InvalidCacheException();
      }
      ArrayList<Contact> contacts = new ArrayList<>();
      for (BddContact bddContact : bddContacts) {
        contacts.add(transformer.transform(bddContact, Contact.class));
      }
      return contacts;
    } catch (java.sql.SQLException e) {
      throw new ObtainContactsBddException();
    } catch (Throwable e) {
      throw new UnknownObtainContactsException();
    }
  }

  @Override public void persist(List<Contact> contacts)
      throws PersistContactsBddException, UnknownPersistContactsException {
    try {
      for (Contact contact : contacts) {
        BddContact bddContact = transformer.transform(contact, BddContact.class);
        bddContact.setPersistedTime(System.currentTimeMillis());
        persistor.persist(bddContact);
      }
    } catch (SQLException e) {
      throw new PersistContactsBddException();
    } catch (Throwable e) {
      throw new UnknownPersistContactsException();
    }
  }

  @Override public Contact obtain(String md5) throws ObtainBddContactException, InvalidCacheException {
    try {
      BddContact bddContact =
          daoContacts.queryBuilder().where().eq(BddContact.FIELD_MD5, md5).queryForFirst();
      if (!cachingStrategy.isValid(bddContact)) {
        throw new InvalidCacheException();
      }
      return transformer.transform(bddContact, Contact.class);
    } catch (Throwable e) {
      throw new ObtainBddContactException();
    }
  }

  @Override public void delete(List<Contact> purgue) throws DeleteContactException {
    if (purgue != null && purgue.size() > 0) {
      try {
        List<String> deleteMd5s = new ArrayList<>();
        for (Contact purgueContact : purgue) {
          deleteMd5s.add(purgueContact.getMd5());
        }
        internalDeleteContacts(deleteMd5s);
      } catch (Throwable e) {
        throw new DeleteContactException();
      }
    }
  }

  private void deleteBddContacts(List<BddContact> purgue) throws SQLException {
    if (purgue != null && purgue.size() > 0) {
      List<String> deleteMd5s = new ArrayList<>();
      for (BddContact purgueContact : purgue) {
        deleteMd5s.add(purgueContact.getMd5());
      }
      internalDeleteContacts(deleteMd5s);
    }
  }

  private void internalDeleteContacts(List<String> deleteMd5s) throws SQLException {
    DeleteBuilder<BddContact, Integer> deleteBuilder = daoContacts.deleteBuilder();
    deleteBuilder.where().in(BddContact.FIELD_MD5, deleteMd5s);
    deleteBuilder.delete();
  }
}

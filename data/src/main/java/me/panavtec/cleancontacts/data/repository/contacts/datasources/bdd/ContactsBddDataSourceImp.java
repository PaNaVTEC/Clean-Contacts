package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd;

import com.j256.ormlite.dao.Dao;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.mappers.BddContactMapper;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors.Persistor;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.entities.abstractmappers.ListMapper;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.CannotObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.PersistContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownPersistContactsException;

import java.sql.SQLException;
import java.util.List;

public class ContactsBddDataSourceImp implements ContactsBddDataSource {

    private final Dao<BddContact, Integer> daoContacts;
    private final Persistor<BddContact> persistor;
    
    private static final BddContactMapper contactMapper = new BddContactMapper();
    private static final ListMapper<Contact, BddContact> listContactMapper = new ListMapper<>(contactMapper);

    public ContactsBddDataSourceImp(Persistor<BddContact> persistor, Dao<BddContact, Integer> daoContacts) {
        this.daoContacts = daoContacts;
        this.persistor = persistor;
    }

    @Override public List<Contact> obtainContacts() throws ObtainContactsBddException, UnknownObtainContactsException {
        try {
            List<BddContact> bddContacts = daoContacts.queryForAll();
            return listContactMapper.dataToModel(bddContacts);
        } catch (java.sql.SQLException e) {
            throw new ObtainContactsBddException();
        } catch (Throwable e) {
            throw new UnknownObtainContactsException();
        }
    }

    @Override public void persist(List<Contact> contacts) throws PersistContactsBddException, UnknownPersistContactsException {
        try {
            for (Contact contact : contacts) {
                BddContact bddContact = contactMapper.modelToData(contact);
                persistor.persist(bddContact);
            }
        } catch (SQLException e) {
            throw new PersistContactsBddException();
        } catch (Throwable e) {
            throw new UnknownPersistContactsException();
        }
    }

    @Override public Contact obtain(String md5) throws CannotObtainBddContactException {

        try {
            BddContact bddContact = daoContacts.queryBuilder().where().eq(BddContact.FIELD_MD5, md5).queryForFirst();
            return contactMapper.dataToModel(bddContact);
        } catch (Throwable e) {
            throw new CannotObtainBddContactException();
        }
    }

}

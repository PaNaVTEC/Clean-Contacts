package me.panavtec.cleancontacts.repository.contacts;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CantRetrieveContactsException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;
import me.panavtec.cleancontacts.repository.caching.strategy.CachingStrategy;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ContactsNetworkException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.PersistContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownPersistContactsException;

public class ContactsRepositoryImp implements ContactsRepository {

  private final ContactsNetworkDataSource networkDataSource;
  private final ContactsBddDataSource bddDataSource;
  private final CachingStrategy<Contact> cachingStrategy;
  private final CachingStrategy<List<Contact>> contactListCachingStrategy;

  public ContactsRepositoryImp(ContactsNetworkDataSource networkDataSource,
      ContactsBddDataSource bddDataSource, CachingStrategy<Contact> cachingStrategy,
      CachingStrategy<List<Contact>> contactListCachingStrategy) {
    this.networkDataSource = networkDataSource;
    this.bddDataSource = bddDataSource;
    this.cachingStrategy = cachingStrategy;
    this.contactListCachingStrategy = contactListCachingStrategy;
  }

  @Override public List<Contact> obtainContacts() throws CantRetrieveContactsException {
    List<Contact> contacts = null;
    try {
      contacts = bddDataSource.obtainContacts();
      if (!contactListCachingStrategy.isValid(contacts)) {
        contacts = networkDataSource.obtainContacts();
        bddDataSource.persist(contacts);
      }
    } catch (ObtainContactsBddException | UnknownObtainContactsException | ContactsNetworkException e) {
      throw new CantRetrieveContactsException();
    } catch (PersistContactsBddException | UnknownPersistContactsException e) {
      e.printStackTrace();
    }
    return contacts;
  }

  @Override public Contact obtain(String md5) throws CannotObtainContactException {
    try {
      Contact contact = bddDataSource.obtain(md5);
      if (!cachingStrategy.isValid(contact)) {
        throw new ObtainBddContactException();
      }
      return contact;
    } catch (ObtainBddContactException e) {
      throw new CannotObtainContactException();
    }
  }
}

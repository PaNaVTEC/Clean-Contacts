package me.panavtec.cleancontacts.repository.contacts;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.ObtainContactException;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ContactsNetworkException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.InvalidCacheException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.PersistContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownPersistContactsException;

public class ContactsRepositoryImp implements ContactsRepository {

  private final ContactsNetworkDataSource networkDataSource;
  private final ContactsBddDataSource bddDataSource;

  public ContactsRepositoryImp(ContactsNetworkDataSource networkDataSource,
      ContactsBddDataSource bddDataSource) {
    this.networkDataSource = networkDataSource;
    this.bddDataSource = bddDataSource;
  }

  @Override public List<Contact> obtainContacts() throws RetrieveContactsException {
    List<Contact> contacts = null;
    try {
      contacts = bddDataSource.obtainContacts();
    } catch (ObtainContactsBddException | InvalidCacheException | UnknownObtainContactsException cacheException) {
      try {
        contacts = networkDataSource.obtainContacts();
        bddDataSource.persist(contacts);
      } catch (UnknownObtainContactsException | ContactsNetworkException e2) {
        throw new RetrieveContactsException();
      } catch (PersistContactsBddException | UnknownPersistContactsException e) {
        e.printStackTrace();
      }
    }
    return contacts;
  }

  @Override public Contact obtain(String md5) throws ObtainContactException {
    try {
      return bddDataSource.obtain(md5);
    } catch (InvalidCacheException | ObtainBddContactException e) {
      throw new ObtainContactException();
    }
  }
}

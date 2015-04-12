package me.panavtec.cleancontacts.repository.contacts.datasources;

import java.util.List;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.DeleteContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.InvalidCacheException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.PersistContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownPersistContactsException;

public interface ContactsBddDataSource {
  List<Contact> obtainContacts()
      throws ObtainContactsBddException, InvalidCacheException, UnknownObtainContactsException;

  void persist(List<Contact> contacts)
      throws PersistContactsBddException, UnknownPersistContactsException;

  Contact obtain(String md5) throws InvalidCacheException, ObtainBddContactException;

  void delete(List<Contact> deleted) throws DeleteContactException;
}

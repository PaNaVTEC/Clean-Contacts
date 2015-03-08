package me.panavtec.cleancontacts.repository.contacts.datasources;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.CannotObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.PersistContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownPersistContactsException;

public interface ContactsBddDataSource {
  public List<Contact> obtainContacts()
      throws ObtainContactsBddException, UnknownObtainContactsException;

  void persist(List<Contact> contacts)
      throws PersistContactsBddException, UnknownPersistContactsException;

  Contact obtain(String md5) throws CannotObtainBddContactException;
}

package me.panavtec.cleancontacts.domain.repository;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CantRetrieveContactsException;

public interface ContactsRepository {
  List<Contact> obtainContacts() throws CantRetrieveContactsException;

  Contact obtain(String md5) throws CannotObtainContactException;
}

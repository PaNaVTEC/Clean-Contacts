package me.panavtec.cleancontacts.domain.repository;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.ObtainContactException;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;

public interface ContactsRepository {
  List<Contact> obtainContacts() throws RetrieveContactsException;

  Contact obtain(String md5) throws ObtainContactException;
}

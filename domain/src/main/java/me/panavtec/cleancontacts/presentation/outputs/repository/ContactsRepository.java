package me.panavtec.cleancontacts.presentation.outputs.repository;

import java.util.List;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.CannotObtainContactException;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.RetrieveContactsException;

public interface ContactsRepository {
  List<Contact> obtainContacts() throws RetrieveContactsException;

  Contact obtain(String md5) throws CannotObtainContactException;
}

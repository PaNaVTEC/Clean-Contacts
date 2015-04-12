package me.panavtec.cleancontacts.repository.contacts.datasources;

import java.util.List;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ContactsNetworkException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;

public interface ContactsNetworkDataSource {

  public List<Contact> obtainContacts()
      throws ContactsNetworkException, UnknownObtainContactsException;
}

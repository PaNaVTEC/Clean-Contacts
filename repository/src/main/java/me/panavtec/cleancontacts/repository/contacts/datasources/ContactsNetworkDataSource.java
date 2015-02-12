package me.panavtec.cleancontacts.repository.contacts.datasources;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ContactsNetworkException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;

import java.util.List;

public interface ContactsNetworkDataSource {

    public List<Contact> obtainContacts() throws ContactsNetworkException, UnknownObtainContactsException;

}

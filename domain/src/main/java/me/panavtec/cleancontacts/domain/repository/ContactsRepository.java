package me.panavtec.cleancontacts.domain.repository;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CantRetrieveContactsException;

import java.util.List;

public interface ContactsRepository {
    List<Contact> obtainContacts() throws CantRetrieveContactsException;
    Contact obtain(String md5) throws CannotObtainContactException;
}

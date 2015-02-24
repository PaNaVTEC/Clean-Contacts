package me.panavtec.cleancontacts.data.bdd;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.CannotObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.PersistContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownPersistContactsException;

import java.util.List;

public class FakeBddDataSource implements ContactsBddDataSource {

    @Override
    public List<Contact> obtainContacts() throws ObtainContactsBddException, UnknownObtainContactsException {
        return null;
    }

    @Override
    public void persist(List<Contact> contacts) throws PersistContactsBddException, UnknownPersistContactsException {
    }

    @Override
    public Contact obtain(String md5) throws CannotObtainBddContactException {
        return null;
    }
}

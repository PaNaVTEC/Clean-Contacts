package me.panavtec.cleancontacts.data.bdd;

import java.util.List;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.DeleteContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.InvalidCacheException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.PersistContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownPersistContactsException;

public class FakeBddDataSource implements ContactsBddDataSource {

    @Override public List<Contact> obtainContacts()
            throws ObtainContactsBddException, UnknownObtainContactsException,
        InvalidCacheException {
        throw new InvalidCacheException();
    }

    @Override public void persist(List<Contact> contacts)
            throws PersistContactsBddException, UnknownPersistContactsException {
    }

    @Override public Contact obtain(String md5) throws InvalidCacheException, ObtainBddContactException {
        throw new InvalidCacheException();
    }

    @Override public void delete(List<Contact> deleted) throws DeleteContactException {
    }
}

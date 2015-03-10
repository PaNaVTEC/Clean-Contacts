package me.panavtec.cleancontacts.repository;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CantRetrieveContactsException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;
import me.panavtec.cleancontacts.repository.caching.strategy.CachingStrategy;
import me.panavtec.cleancontacts.repository.caching.strategy.ListCachingStrategy;
import me.panavtec.cleancontacts.repository.contacts.ContactsRepositoryImp;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ContactsNetworkException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;

import static org.mockito.Mockito.when;

public class ContactRepositoryTest {

    private static final String FAKE_CONTACT_MD5 = "abcdefghijkmnopqrstuvwxyz";

    @Mock private ContactsBddDataSource mockContactsBddDataSource;
    @Mock private ContactsNetworkDataSource mockContactsNetworkDataSource;
    private CachingStrategy<Contact> contactCachingStrategy = new NotValidCachingStrategyStub();
    private ListCachingStrategy<Contact> listCachingStrategy = new ListCachingStrategy<>(contactCachingStrategy);

    private ContactsRepository contactsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        try {
            when(mockContactsBddDataSource.obtain(FAKE_CONTACT_MD5)).thenReturn(new Contact());
            when(mockContactsBddDataSource.obtainContacts()).thenReturn(Arrays.asList(new Contact(), new Contact(), new Contact(), new Contact(), new Contact()));
            when(mockContactsNetworkDataSource.obtainContacts()).thenReturn(Arrays.asList(new Contact(), new Contact(), new Contact()));
        } catch (ObtainBddContactException | ContactsNetworkException | UnknownObtainContactsException | ObtainContactsBddException ignored) {
        }

        contactsRepository = new ContactsRepositoryImp(mockContactsNetworkDataSource, mockContactsBddDataSource, contactCachingStrategy, listCachingStrategy);

    }

    @Test
    public void testObtainContacts() {
        try {
            List<Contact> contacts = contactsRepository.obtainContacts();
            Assert.assertTrue(contacts != null && contacts.size() == 3);
        } catch (CantRetrieveContactsException ignored) {
        }
    }

    @Test
    public void testObtainContact() {
        try {
            Contact contact = contactsRepository.obtain(FAKE_CONTACT_MD5);
            Assert.assertTrue(contact != null);
        } catch (CannotObtainContactException ignored) {
        }
    }

}

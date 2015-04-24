package me.panavtec.cleancontacts.repository.contacts;

import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.ObtainContactException;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ContactsNetworkException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.InvalidCacheException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainBddContactException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ObtainContactsBddException;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.UnknownObtainContactsException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class ContactRepositoryTest {

  private static final String FAKE_CONTACT_MD5 = "abcdefghijkmnopqrstuvwxyz";

  @Mock private ContactsBddDataSource mockContactsBddDataSource;
  @Mock private ContactsNetworkDataSource mockContactsNetworkDataSource;

  private ContactsRepository contactsRepository;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    contactsRepository =
        new ContactsRepositoryImp(mockContactsNetworkDataSource, mockContactsBddDataSource);
  }

  @Test public void testObtainContactsWithInvalidCache() {
    try {
      when(mockContactsBddDataSource.obtainContacts()).thenThrow(new InvalidCacheException());
      when(mockContactsNetworkDataSource.obtainContacts()).thenReturn(
          Arrays.asList(new Contact(), new Contact(), new Contact()));
      List<Contact> contacts = contactsRepository.obtainContacts();
      Assert.assertTrue(contacts != null && contacts.size() == 3);
    } catch (InvalidCacheException | UnknownObtainContactsException | ContactsNetworkException | ObtainContactsBddException | RetrieveContactsException ignored) {
    }
  }

  @Test public void testObtainContactsWithValidCache() {
    try {
      when(mockContactsBddDataSource.obtainContacts()).thenReturn(
          Arrays.asList(new Contact(), new Contact(), new Contact(), new Contact(), new Contact()));
      when(mockContactsNetworkDataSource.obtainContacts()).thenReturn(
          Arrays.asList(new Contact(), new Contact(), new Contact()));
      List<Contact> contacts = contactsRepository.obtainContacts();
      Assert.assertTrue(contacts != null && contacts.size() == 5);
    } catch (InvalidCacheException | UnknownObtainContactsException | ContactsNetworkException | ObtainContactsBddException | RetrieveContactsException ignored) {
    }
  }

  @Test public void testObtainContact() {
    try {
      when(mockContactsBddDataSource.obtain(FAKE_CONTACT_MD5)).thenReturn(new Contact());
      Contact contact = contactsRepository.obtain(FAKE_CONTACT_MD5);
      Assert.assertTrue(contact != null);
    } catch (InvalidCacheException | ObtainBddContactException | ObtainContactException ignored) {
    }
  }
}

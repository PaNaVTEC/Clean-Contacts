package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.Collections;
import java.util.List;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;
import me.panavtec.cleancontacts.domain.model.ContactsNetworkGateway;
import me.panavtec.cleancontacts.domain.model.LocalException;
import me.panavtec.cleancontacts.domain.model.NetworkException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) public class GetContactsInteractorShould {

  private static final List<Contact> EMPTY_LIST = Collections.emptyList();
  public static final List<Contact> CONTACTS = Collections.singletonList(new Contact());

  @Mock ContactsLocalGateway localGateway;
  @Mock ContactsNetworkGateway networkGateway;

  private GetContactsInteractor interactor;

  @Before public void initialise() {
    interactor = new GetContactsInteractor(localGateway, networkGateway);
  }

  @Test public void update_cache_when_hit_network() throws Exception {
    when(localGateway.obtainContacts()).thenReturn(EMPTY_LIST);
    when(networkGateway.obtainContacts()).thenReturn(CONTACTS);

    interactor.call();

    verify(localGateway).persist(CONTACTS);
  }

  @Test public void return_networks_contacts_when_network_success() {
    when(localGateway.obtainContacts()).thenReturn(EMPTY_LIST);
    when(networkGateway.obtainContacts()).thenReturn(CONTACTS);

    InteractorResponse<List<Contact>> response = interactor.call();

    assertThatResponseHasResult(response);
  }

  @Test public void return_local_contacts_when_local_storage_is_populated() {
    when(localGateway.obtainContacts()).thenReturn(CONTACTS);

    InteractorResponse<List<Contact>> response = interactor.call();

    assertThatResponseHasResult(response);
  }

  @Test public void return_an_error_when_network_fails() {
    when(localGateway.obtainContacts()).thenReturn(EMPTY_LIST);
    when(networkGateway.obtainContacts()).thenThrow(new NetworkException());

    InteractorResponse<List<Contact>> result = interactor.call();

    assertThatResponseHasError(result);
  }

  @Test public void hit_network_when_local_fails() {
    when(localGateway.obtainContacts()).thenThrow(new LocalException());

    interactor.call();

    verify(networkGateway).obtainContacts();
  }

  @Test public void hit_network_when_local_is_empty() {
    when(localGateway.obtainContacts()).thenReturn(EMPTY_LIST);

    interactor.call();

    verify(networkGateway).obtainContacts();
  }

  private void assertThatResponseHasResult(InteractorResponse<List<Contact>> response) {
    assertThat(response.getError(), is(nullValue()));
    assertThat(response.getResult(), is(CONTACTS));
  }

  private void assertThatResponseHasError(InteractorResponse<List<Contact>> result) {
    assertThat(result.getError(), is(notNullValue()));
    assertThat(result.getResult(), is(nullValue()));
  }
}
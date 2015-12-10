package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.Collections;
import java.util.List;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactProvider;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;
import me.panavtec.cleancontacts.domain.model.ContactsNetworkGateway;

public class GetContactsInteractor implements Interactor<InteractorResponse<List<Contact>>> {

  private ContactsLocalGateway localGateway;
  private ContactsNetworkGateway networkGateway;

  public GetContactsInteractor(ContactsLocalGateway localGateway,
      ContactsNetworkGateway networkGateway) {
    this.localGateway = localGateway;
    this.networkGateway = networkGateway;
  }

  @Override public InteractorResponse<List<Contact>> call() {
    return getContacts(localGateway, networkGateway);
  }

  private InteractorResponse<List<Contact>> getContacts(ContactProvider... providers) {
    for (int j = 0; j < providers.length; j++) {
      try {
        ContactProvider provider = providers[j];
        List<Contact> contacts = provider.obtainContacts();
        if (!contacts.isEmpty()) {
          return new InteractorResponse<>(contacts);
        }
      } catch (RuntimeException e) {
        boolean isLast = j == providers.length - 1;
        if (isLast) {
          return new InteractorResponse<>(new GetContactsError());
        }
      }
    }
    return new InteractorResponse<>(Collections.<Contact>emptyList());
  }

  private InteractorResponse<List<Contact>> recursiveGetContacts(int nextProvider, ContactProvider... providers) {
    boolean isLast = nextProvider == providers.length - 1;
    ContactProvider provider = providers[nextProvider];
    try {
      List<Contact> contacts = provider.obtainContacts();
      if (!contacts.isEmpty()) {
        return new InteractorResponse<>(contacts);
      } else if (isLast) {
        return new InteractorResponse<>(Collections.<Contact>emptyList());
      }
    } catch (RuntimeException e) {
      if (isLast) {
        return new InteractorResponse<>(new GetContactsError());
      }
    }
    return recursiveGetContacts(++nextProvider, providers);
  }
}

package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.List;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;
import me.panavtec.cleancontacts.domain.model.ContactsNetworkGateway;
import me.panavtec.cleancontacts.domain.model.LocalException;
import me.panavtec.cleancontacts.domain.model.NetworkException;

public class GetContactsInteractor implements Interactor<InteractorResponse<List<Contact>>> {

  private ContactsLocalGateway localGateway;
  private ContactsNetworkGateway networkGateway;

  public GetContactsInteractor(ContactsLocalGateway localGateway,
      ContactsNetworkGateway networkGateway) {
    this.localGateway = localGateway;
    this.networkGateway = networkGateway;
  }

  @Override public InteractorResponse<List<Contact>> call() {
    try {
      List<Contact> contacts = localGateway.obtainContacts();
      try {
        if (contacts == null) {
          contacts = networkGateway.obtainContacts();
          localGateway.persist(contacts);
        }
      } catch (NetworkException e) {
        return new InteractorResponse<>(new GetContactError());
      }
      return new InteractorResponse<>(contacts);
    } catch (LocalException e) {
      return new InteractorResponse<>(new GetContactError());
    }
  }
}

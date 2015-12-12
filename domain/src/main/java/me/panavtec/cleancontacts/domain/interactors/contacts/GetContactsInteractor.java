package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.List;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.interactors.contacts.Chain.TypeProvider;
import me.panavtec.cleancontacts.domain.interactors.contacts.Chain.TypeStorer;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;
import me.panavtec.cleancontacts.domain.model.ContactsNetworkGateway;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class GetContactsInteractor implements Interactor<InteractorResponse<List<Contact>>> {

  private final ContactsLocalGateway localGateway;
  private final ContactsNetworkGateway networkGateway;

  public GetContactsInteractor(ContactsLocalGateway localGateway,
      ContactsNetworkGateway networkGateway) {
    this.localGateway = localGateway;
    this.networkGateway = networkGateway;
  }

  @Override public InteractorResponse<List<Contact>> call() {
    return new Chain.Builder<List<Contact>>().typeChecker(contactListTypeChcker())
        .providers(asList(localGateway(), networkGateway()))
        .storers(singletonList(localGatewayStore()))
        .build()
        .obtain();
  }

  private ListTypeChecker<Contact> contactListTypeChcker() {
    return new ListTypeChecker<>(new GetContactsError());
  }

  private TypeStorer<List<Contact>> localGatewayStore() {
    return new TypeStorer<List<Contact>>() {
      @Override public void store(List<Contact> contacts) {
        localGateway.persist(contacts);
      }
    };
  }

  private TypeProvider<List<Contact>> networkGateway() {
    return new TypeProvider<List<Contact>>() {
      @Override public List<Contact> obtain() {
        return networkGateway.obtainContacts();
      }
    };
  }

  private TypeProvider<List<Contact>> localGateway() {
    return new TypeProvider<List<Contact>>() {
      @Override public List<Contact> obtain() {
        return localGateway.obtainContacts();
      }
    };
  }
}

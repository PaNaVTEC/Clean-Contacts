package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.Collections;
import java.util.List;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorError;
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
    return new Chain<>(new TypeChecker<List<Contact>>() {
      @Override public boolean isValid(List<Contact> object) {
        return object.isEmpty();
      }

      @Override public List<Contact> empty() {
        return Collections.emptyList();
      }

      @Override public InteractorError error() {
        return new GetContactsError();
      }
    }, new TypeProvider<List<Contact>>() {
      @Override public List<Contact> obtain() {
        return localGateway.obtainContacts();
      }
    }, new TypeProvider<List<Contact>>() {
      @Override public List<Contact> obtain() {
        return networkGateway.obtainContacts();
      }
    }).recursiveGetContacts(0);
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

  static class Chain<T> {

    private TypeChecker<T> typeChecker;
    private TypeProvider<T>[] providers;

    public Chain(TypeChecker<T> typeChecker, TypeProvider<T>... providers) {
      this.typeChecker = typeChecker;
      this.providers = providers;
    }

    private InteractorResponse<T> recursiveGetContacts(int nextProvider) {
      boolean isLast = nextProvider == providers.length - 1;
      TypeProvider<T> provider = providers[nextProvider];
      try {
        T contacts = provider.obtain();
        if (!typeChecker.isValid(contacts)) {
          return new InteractorResponse<>(contacts);
        } else if (isLast) {
          return new InteractorResponse<>(typeChecker.empty());
        }
      } catch (RuntimeException e) {
        if (isLast) {
          return new InteractorResponse<>(typeChecker.error());
        }
      }
      return recursiveGetContacts(++nextProvider);
    }
  }

  interface TypeProvider<T> {
    T obtain();
  }

  interface TypeChecker<T> {
    boolean isValid(T object);

    T empty();

    InteractorError error();
  }
}

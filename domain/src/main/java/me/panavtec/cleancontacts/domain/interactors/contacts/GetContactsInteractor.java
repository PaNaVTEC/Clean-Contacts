package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;

public class GetContactsInteractor implements Interactor<InteractorResponse<List<Contact>>> {

  private ContactsRepository repository;

  public GetContactsInteractor(ContactsRepository repository) {
    this.repository = repository;
  }

  @Override public InteractorResponse<List<Contact>> call() {
    try {
      return new InteractorResponse<>(repository.obtainContacts());
    } catch (RetrieveContactsException e) {
      return new InteractorResponse<>(new GetContactsError());
    }
  }
}

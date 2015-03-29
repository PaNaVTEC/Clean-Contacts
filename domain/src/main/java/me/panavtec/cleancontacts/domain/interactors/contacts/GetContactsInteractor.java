package me.panavtec.cleancontacts.domain.interactors.contacts;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.Interactor;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;

public class GetContactsInteractor implements Interactor<List<Contact>, RetrieveContactsException> {

  private ContactsRepository repository;

  public GetContactsInteractor(ContactsRepository repository) {
    this.repository = repository;
  }

  @Override public void execute(InteractorOutput<List<Contact>, RetrieveContactsException> output) {
    try {
      output.onResult(repository.obtainContacts());
    } catch (RetrieveContactsException error) {
      output.onError(error);
    }
  }
}

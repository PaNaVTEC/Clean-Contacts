package me.panavtec.cleancontacts.presentation.outputs.interactors.contacts;

import java.util.List;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.Interactor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.RetrieveContactsException;
import me.panavtec.cleancontacts.presentation.outputs.repository.ContactsRepository;

public class GetContactsInteractor implements Interactor<List<Contact>, RetrieveContactsException> {

  private ContactsRepository repository;

  public GetContactsInteractor(ContactsRepository repository) {
    this.repository = repository;
  }

  @Override public List<Contact> call() throws RetrieveContactsException {
    return repository.obtainContacts();
  }
}

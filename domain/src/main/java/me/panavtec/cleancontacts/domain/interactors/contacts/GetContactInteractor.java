package me.panavtec.cleancontacts.domain.interactors.contacts;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.ObtainContactException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;

public class GetContactInteractor implements Interactor<InteractorResponse<Contact>> {

  private ContactsRepository repository;
  private String contactMd5;

  public GetContactInteractor(ContactsRepository repository) {
    this.repository = repository;
  }

  public void setData(String contactMd5) {
    this.contactMd5 = contactMd5;
  }

  @Override public InteractorResponse<Contact> call() {
    try {
      return new InteractorResponse<>(repository.obtain(contactMd5));
    } catch (ObtainContactException e) {
      return new InteractorResponse<>(new GetContactError() { });
    }
  }
}

package me.panavtec.cleancontacts.domain.interactors.contacts;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.Interactor;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;

public class GetContactInteractor implements Interactor<Contact, CannotObtainContactException> {

  private ContactsRepository repository;
  private String contactMd5;

  public GetContactInteractor(ContactsRepository repository) {
    this.repository = repository;
  }

  public void setData(String contactMd5) {
    this.contactMd5 = contactMd5;
  }

  @Override public void execute(InteractorOutput<Contact, CannotObtainContactException> output) {
    try {
      output.onResult(repository.obtain(contactMd5));
    } catch (CannotObtainContactException error) {
      output.onError(error);
    }
  }
}

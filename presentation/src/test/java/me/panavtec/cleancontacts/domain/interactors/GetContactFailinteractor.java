package me.panavtec.cleancontacts.domain.interactors;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;

public class GetContactFailInteractor extends GetContactInteractor {
  public GetContactFailInteractor() {
    super(null);
  }

  @Override public void execute(InteractorOutput<Contact, CannotObtainContactException> output) {
    output.onError(new CannotObtainContactException());
  }
}

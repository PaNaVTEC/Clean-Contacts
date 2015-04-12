package me.panavtec.cleancontacts.presentation.outputs.interactors;

import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.CannotObtainContactException;

public class GetContactFailinteractor extends GetContactInteractor {
  public GetContactFailinteractor() {
    super(null);
  }

  @Override public void execute(InteractorOutput<Contact, CannotObtainContactException> output) {
    output.onError(new CannotObtainContactException());
  }
}

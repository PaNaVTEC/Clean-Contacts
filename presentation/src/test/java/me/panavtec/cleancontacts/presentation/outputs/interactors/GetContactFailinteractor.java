package me.panavtec.cleancontacts.presentation.outputs.interactors;

import me.panavtec.presentation.common.InteractorOutput;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.ObtainContactException;

public class GetContactFailinteractor extends GetContactInteractor {
  public GetContactFailinteractor() {
    super(null);
  }

  @Override public void execute(InteractorOutput<Contact, ObtainContactException> output) {
    output.onError(new ObtainContactException());
  }
}

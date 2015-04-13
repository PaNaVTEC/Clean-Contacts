package me.panavtec.cleancontacts.presentation.outputs.interactors;

import java.util.List;
import me.panavtec.presentation.common.outputs.InteractorOutput;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.RetrieveContactsException;

public class GetContactsFailInteractor extends GetContactsInteractor {
  public GetContactsFailInteractor() {
    super(null);
  }

  @Override public void execute(InteractorOutput<List<Contact>, RetrieveContactsException> output) {
    output.onError(new RetrieveContactsException());
  }
}

package me.panavtec.cleancontacts.presentation.outputs.interactors;

import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.RetrieveContactsException;

public class GetContactsSuccessInteractor extends GetContactsInteractor {

  public GetContactsSuccessInteractor() {
    super(null);
  }

  @Override public void execute(InteractorOutput<List<Contact>, RetrieveContactsException> output) {
    output.onResult(new ArrayList<Contact>());
  }
}

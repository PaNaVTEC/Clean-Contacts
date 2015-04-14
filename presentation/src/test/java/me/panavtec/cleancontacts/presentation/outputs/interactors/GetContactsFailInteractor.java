package me.panavtec.cleancontacts.presentation.outputs.interactors;

import java.util.List;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.RetrieveContactsException;

public class GetContactsFailInteractor extends GetContactsInteractor {
  public GetContactsFailInteractor() {
    super(null);
  }

  @Override public List<Contact> call() throws RetrieveContactsException {
    throw new RetrieveContactsException();
  }
}

package me.panavtec.cleancontacts.presentation.interactors;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;

public class GetContactsFailInteractor extends GetContactsInteractor {
  public GetContactsFailInteractor() {
    super(null);
  }

  @Override public List<Contact> call() throws RetrieveContactsException {
    throw new RetrieveContactsException();
  }
}

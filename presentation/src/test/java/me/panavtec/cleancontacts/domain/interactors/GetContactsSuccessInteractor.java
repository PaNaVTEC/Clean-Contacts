package me.panavtec.cleancontacts.domain.interactors;

import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;

public class GetContactsSuccessInteractor extends GetContactsInteractor {

  public GetContactsSuccessInteractor() {
    super(null);
  }

  @Override public List<Contact> call() throws RetrieveContactsException {
    return new ArrayList<>();
  }
}

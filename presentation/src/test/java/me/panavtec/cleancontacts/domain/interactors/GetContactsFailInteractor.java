package me.panavtec.cleancontacts.domain.interactors;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;

public class GetContactsFailInteractor extends GetContactsInteractor {
  public GetContactsFailInteractor() {
    super(null);
  }

  @Override public void execute(InteractorOutput<List<Contact>, RetrieveContactsException> output) {
    output.onError(new RetrieveContactsException());
  }
}

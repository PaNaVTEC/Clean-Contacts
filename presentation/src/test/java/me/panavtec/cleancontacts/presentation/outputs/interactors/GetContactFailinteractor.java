package me.panavtec.cleancontacts.presentation.outputs.interactors;

import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.ObtainContactException;

public class GetContactFailinteractor extends GetContactInteractor {
  public GetContactFailinteractor() {
    super(null);
  }

  @Override public Contact call() throws ObtainContactException{
    throw new ObtainContactException();
  }
}

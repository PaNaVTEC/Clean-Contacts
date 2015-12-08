package me.panavtec.cleancontacts.presentation.interactors;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.ObtainContactException;

public class GetContactFailinteractor extends GetContactInteractor {
  public GetContactFailinteractor() {
    super(null);
  }

  @Override public Contact call() throws ObtainContactException{
    throw new ObtainContactException();
  }
}

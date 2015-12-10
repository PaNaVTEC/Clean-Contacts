package me.panavtec.cleancontacts.domain.interactors.contacts;

import me.panavtec.cleancontacts.domain.interactors.InteractorError;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.model.Contact;

public class GetContactResponse extends InteractorResponse<Contact> {
  public GetContactResponse(InteractorError error) {
    super(error);
  }

  public GetContactResponse(Contact result) {
    super(result);
  }
}

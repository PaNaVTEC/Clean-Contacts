package me.panavtec.cleancontacts.presentation.outputs.interactors;

import me.panavtec.presentation.common.InteractorOutput;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.ObtainContactException;

public class GetContactSuccessInteractor extends GetContactInteractor {
  
  private String contactMd5;
  
  public GetContactSuccessInteractor() {
    super(null);
  }

  @Override public void setData(String contactMd5) {
    super.setData(contactMd5);
    this.contactMd5 = contactMd5;
  }

  @Override public void execute(InteractorOutput<Contact, ObtainContactException> output) {
    Contact contact = new Contact();
    contact.setMd5(contactMd5);
    output.onResult(contact);
  }
}

package me.panavtec.cleancontacts.domain.interactors;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;

public class GetContactSuccessInteractor extends GetContactInteractor {
  
  private String contactMd5;
  
  public GetContactSuccessInteractor() {
    super(null);
  }

  @Override public void setData(String contactMd5) {
    super.setData(contactMd5);
    this.contactMd5 = contactMd5;
  }

  @Override public void execute(InteractorOutput<Contact, CannotObtainContactException> output) {
    Contact contact = new Contact();
    contact.setMd5(contactMd5);
    output.onResult(contact);
  }
}

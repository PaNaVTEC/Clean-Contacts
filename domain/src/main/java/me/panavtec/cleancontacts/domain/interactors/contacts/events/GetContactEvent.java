package me.panavtec.cleancontacts.domain.interactors.contacts.events;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.BaseEvent;

public class GetContactEvent extends BaseEvent {

  private Contact contact;

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }
}

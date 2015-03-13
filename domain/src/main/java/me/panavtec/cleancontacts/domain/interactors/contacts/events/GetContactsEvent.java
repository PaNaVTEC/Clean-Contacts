package me.panavtec.cleancontacts.domain.interactors.contacts.events;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.BaseEvent;

public class GetContactsEvent extends BaseEvent {

  private List<Contact> contacts;

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }
}

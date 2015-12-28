package me.panavtec.cleancontacts.data;

import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;

public class InMemoryContactGateway implements ContactsLocalGateway {

  private final List<Contact> contacts = new ArrayList<>();

  @Override public void persist(List<Contact> contacts) {
    this.contacts.addAll(contacts);
  }

  @Override public Contact obtain(String md5) {
    for (Contact c : contacts) {
      if (c.getMd5().equals(md5)) return c;
    }
    return null;
  }

  @Override public void delete(List<Contact> deleted) {
    contacts.removeAll(deleted);
  }

  @Override public List<Contact> obtainContacts() {
    return contacts;
  }
}

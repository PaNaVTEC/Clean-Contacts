package me.panavtec.cleancontacts.domain.model;

import java.util.List;

public interface ContactProvider {
  List<Contact> obtainContacts();
}

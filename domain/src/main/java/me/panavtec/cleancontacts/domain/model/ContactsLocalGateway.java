package me.panavtec.cleancontacts.domain.model;

import java.util.List;

public interface ContactsLocalGateway extends ContactProvider {

  void persist(List<Contact> contacts);

  Contact obtain(String md5);

  void delete(List<Contact> deleted);
}

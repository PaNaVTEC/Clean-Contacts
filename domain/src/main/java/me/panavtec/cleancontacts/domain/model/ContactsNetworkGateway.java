package me.panavtec.cleancontacts.domain.model;

import java.util.List;

public interface ContactsNetworkGateway {

  List<Contact> obtainContacts();
}

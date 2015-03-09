package me.panavtec.cleancontacts.repository.contacts.caching;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.repository.caching.strategy.CachingStrategy;

public class ContactsNotNullCachingStrategy implements CachingStrategy<Contact> {
  @Override public boolean isValid(Contact data) {
    return data != null;
  }
}

package me.panavtec.cleancontacts.repository.contacts;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.cachingstrategy.TimeUnit;
import me.panavtec.cleancontacts.repository.contacts.cachingstrategy.TtlCachingStrategy;

public class ContactCachingStrategy extends TtlCachingStrategy<Contact> {

  public ContactCachingStrategy(int ttl, TimeUnit timeUnit) {
    super(ttl, timeUnit);
  }

  @Override protected long getTime(Contact data) {
    return data.getPersistedTime();
  }

}

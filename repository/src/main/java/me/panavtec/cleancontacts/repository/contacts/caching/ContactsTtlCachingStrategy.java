package me.panavtec.cleancontacts.repository.contacts.caching;

import java.util.concurrent.TimeUnit;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.repository.caching.strategy.TtlCachingStrategy;

public class ContactsTtlCachingStrategy extends TtlCachingStrategy<Contact> {
  public ContactsTtlCachingStrategy(int ttl, TimeUnit timeUnit) {
    super(ttl, timeUnit);
  }

  @Override protected long getTime(Contact data) {
    return data.getPersistedTime();
  }
}

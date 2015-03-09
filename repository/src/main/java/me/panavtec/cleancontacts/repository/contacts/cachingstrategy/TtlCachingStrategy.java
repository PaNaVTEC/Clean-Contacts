package me.panavtec.cleancontacts.repository.contacts.cachingstrategy;

import java.util.Collection;

public abstract class TtlCachingStrategy<T> implements CachingStrategy<T> {

  private final int ttl;

  public TtlCachingStrategy(int ttl, TimeUnit timeUnit) {
    this.ttl = ttl * timeUnit.toMillis();
  }

  @Override public boolean isValid(T data) {
    return getTime(data) + ttl > System.currentTimeMillis();
  }

  @Override public boolean isValid(Collection<T> dataCollection) {
    if (dataCollection == null || dataCollection.size() == 0) {
      return false;
    }
    for (T data : dataCollection) {
      if (!isValid(data)) {
        return false;
      }
    }
    return true;
  }

  protected abstract long getTime(T data);
}

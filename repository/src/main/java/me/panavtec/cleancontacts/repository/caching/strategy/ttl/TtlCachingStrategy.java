package me.panavtec.cleancontacts.repository.caching.strategy.ttl;

import java.util.concurrent.TimeUnit;
import me.panavtec.cleancontacts.repository.caching.strategy.CachingStrategy;

public class TtlCachingStrategy<T extends TtlCachingObject> implements CachingStrategy<T> {

  private final long ttlMillis;

  public TtlCachingStrategy(int ttl, TimeUnit timeUnit) {
    ttlMillis = timeUnit.toMillis(ttl);
  }

  @Override public boolean isValid(T data) {
    return (data.getPersistedTime() + ttlMillis) > System.currentTimeMillis();
  }
  
}

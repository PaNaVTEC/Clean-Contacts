package me.panavtec.cleancontacts.repository.caching.strategy;

import java.util.concurrent.TimeUnit;

public abstract class TtlCachingStrategy<T> implements CachingStrategy<T> {

  private final long ttlMillis;

  public TtlCachingStrategy(int ttl, TimeUnit timeUnit) {
    ttlMillis = timeUnit.toMillis(ttl);
  }

  @Override public boolean isValid(T data) {
    return (getTime(data) + ttlMillis) > System.currentTimeMillis();
  }

  /**
   * Gets the timestamp to compare in milliseconds
   * @param data object where to get data
   * @return the milliseconds to compare
   */
  protected abstract long getTime(T data);
}

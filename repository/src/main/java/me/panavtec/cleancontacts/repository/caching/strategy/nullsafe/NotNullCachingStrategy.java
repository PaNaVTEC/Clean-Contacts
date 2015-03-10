package me.panavtec.cleancontacts.repository.caching.strategy.nullsafe;

import me.panavtec.cleancontacts.repository.caching.strategy.CachingStrategy;

public class NotNullCachingStrategy<T> implements CachingStrategy<T> {
  @Override public boolean isValid(T data) {
    return data != null;
  }
}

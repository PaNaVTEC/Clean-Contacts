package me.panavtec.cleancontacts.data.repository.caching.strategy.nullsafe;

import me.panavtec.cleancontacts.data.repository.caching.strategy.CachingStrategy;

public class NotNullCachingStrategy<T> implements CachingStrategy<T> {
  @Override public boolean isValid(T data) {
    return data != null;
  }
}

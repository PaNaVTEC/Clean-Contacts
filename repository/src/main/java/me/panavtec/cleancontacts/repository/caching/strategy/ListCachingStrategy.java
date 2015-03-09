package me.panavtec.cleancontacts.repository.caching.strategy;

import java.util.List;

public class ListCachingStrategy<T> implements CachingStrategy<List<T>> {

  private final CachingStrategy<T> cachingStrategy;

  public ListCachingStrategy(CachingStrategy<T> cachingStrategy) {
    this.cachingStrategy = cachingStrategy;
  }

  @Override public boolean isValid(List<T> data) {
    if (data == null || data.size() == 0) {
      return false;
    }
    
    for (T single : data) {
      if (!cachingStrategy.isValid(single)) {
        return false;
      }
    }
    return true;
  }

}

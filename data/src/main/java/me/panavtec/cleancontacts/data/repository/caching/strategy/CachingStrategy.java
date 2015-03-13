package me.panavtec.cleancontacts.data.repository.caching.strategy;

public interface CachingStrategy<T> {
  boolean isValid(T data);
}

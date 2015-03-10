package me.panavtec.cleancontacts.repository.caching.strategy;

public interface CachingStrategy<T> {
  boolean isValid(T data);
}

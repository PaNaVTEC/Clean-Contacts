package me.panavtec.cleancontacts.repository.contacts.cachingstrategy;

import java.util.Collection;

public interface CachingStrategy<T> {
    boolean isValid(T data);
    boolean isValid (Collection<T> dataList);
}

package me.panavtec.cleancontacts.domain.repository;

import java.util.Collection;

public interface CachingStrategy<T> {
    boolean isValid(T data);
    boolean isValid (Collection<T> dataList);
}

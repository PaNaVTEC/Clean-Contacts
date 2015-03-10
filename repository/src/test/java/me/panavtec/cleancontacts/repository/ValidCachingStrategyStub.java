package me.panavtec.cleancontacts.repository;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.repository.caching.strategy.CachingStrategy;

public class ValidCachingStrategyStub implements CachingStrategy<Contact> {
    @Override
    public boolean isValid(Contact data) {
        return true;
    }
}

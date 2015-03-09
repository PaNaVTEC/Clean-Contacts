package me.panavtec.cleancontacts.repository.contacts;

import java.util.Collection;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.repository.CachingStrategy;

public class ContactCachingStrategy implements CachingStrategy<Contact> {

    @Override
    public boolean isValid(Contact data) {
        return data != null;
    }

    @Override
    public boolean isValid(Collection<Contact> dataList) {
        return dataList != null && dataList.size() > 0;
    }
    
}

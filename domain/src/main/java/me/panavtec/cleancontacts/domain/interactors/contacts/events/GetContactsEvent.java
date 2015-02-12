package me.panavtec.cleancontacts.domain.interactors.contacts.events;

import me.panavtec.cleancontacts.domain.interactors.BaseEvent;
import me.panavtec.cleancontacts.domain.entities.Contact;

import java.util.List;

public class GetContactsEvent extends BaseEvent {

    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}

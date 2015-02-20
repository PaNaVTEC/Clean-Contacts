package me.panavtec.cleancontacts.domain.interactors.contacts;

import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.contacts.events.GetContactEvent;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;

public class GetContactInteractor implements Interactor {

    private Bus bus;
    private ContactsRepository repository;
    private String contactMd5;

    public GetContactInteractor(Bus bus, ContactsRepository repository) {
        this.bus = bus;
        this.repository = repository;
    }

    public void setData(String contactMd5) {
        this.contactMd5 = contactMd5;
    }

    @Override public void execute() {
        GetContactEvent event = new GetContactEvent();
        try {
            Contact contact = repository.obtain(contactMd5);
            event.setContact(contact);
        } catch (CannotObtainContactException e) {
            event.setError(e);
        }
        bus.post(event);
    }

}

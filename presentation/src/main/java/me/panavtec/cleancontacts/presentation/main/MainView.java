package me.panavtec.cleancontacts.presentation.main;

import java.util.List;

import me.panavtec.cleancontacts.domain.entities.Contact;

public interface MainView {
    void showGetContactsError();
    void refreshContactsList(List<Contact> contacts);
}

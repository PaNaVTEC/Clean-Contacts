package me.panavtec.cleancontacts.presentation.main;

import me.panavtec.cleancontacts.domain.entities.Contact;

import java.util.List;

public interface MainView {
    void showGetContactsError();
    void refreshContactsList(List<Contact> contacts);
    void clearData();
}

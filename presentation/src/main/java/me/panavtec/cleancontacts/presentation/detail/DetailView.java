package me.panavtec.cleancontacts.presentation.detail;

import me.panavtec.cleancontacts.domain.entities.Contact;

public interface DetailView {
    void showContactData(Contact contact);
    void showGetContactError();
}

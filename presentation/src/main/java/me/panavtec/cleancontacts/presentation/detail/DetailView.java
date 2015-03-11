package me.panavtec.cleancontacts.presentation.detail;

import me.panavtec.cleancontacts.presentation.model.PresentationContact;

public interface DetailView {
  void showContactData(PresentationContact contact);

  void showGetContactError();
}

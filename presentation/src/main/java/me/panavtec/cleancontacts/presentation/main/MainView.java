package me.panavtec.cleancontacts.presentation.main;

import java.util.List;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;

public interface MainView {
  void showGetContactsError();

  void refreshContactsList(List<PresentationContact> contacts);

  void refreshUi();

  void initUi();
}

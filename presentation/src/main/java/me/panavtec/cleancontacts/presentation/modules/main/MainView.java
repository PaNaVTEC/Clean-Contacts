package me.panavtec.cleancontacts.presentation.modules.main;

import java.util.List;
import me.panavtec.cleancontacts.presentation.PresenterView;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;

public interface MainView extends PresenterView {
  void showGetContactsError();

  void refreshContactsList(List<PresentationContact> contacts);

  void refreshUi();
}

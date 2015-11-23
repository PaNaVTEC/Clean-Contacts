package me.panavtec.cleancontacts.presentation.modules.main;

import java.util.List;

import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.threaddecoratedview.views.qualifiers.NotDecorated;

public interface MainView {

  @NotDecorated
  void initUi();

  void showGetContactsError();

  void refreshContactsList(List<PresentationContact> contacts);

  void refreshUi();
}

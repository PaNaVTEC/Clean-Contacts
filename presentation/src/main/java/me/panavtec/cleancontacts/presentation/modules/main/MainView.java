package me.panavtec.cleancontacts.presentation.modules.main;

import java.util.List;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.threaddecoratedview.views.qualifiers.NotDecorated;
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView;

@ThreadDecoratedView public interface MainView {

  @NotDecorated void initUi();

  void showGetContactsError();

  void refreshContactsList(List<PresentationContact> contacts);

  void refreshUi();
}

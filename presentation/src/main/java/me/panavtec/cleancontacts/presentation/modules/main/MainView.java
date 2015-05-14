package me.panavtec.cleancontacts.presentation.modules.main;

import java.util.List;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.presentation.common.views.qualifiers.NoDecorate;
import me.panavtec.presentation.common.views.qualifiers.ThreadDecoratedView;

@ThreadDecoratedView
public interface MainView {

  @NoDecorate void initUi();

  void showGetContactsError();

  void refreshContactsList(List<PresentationContact> contacts);

  void refreshUi();
}

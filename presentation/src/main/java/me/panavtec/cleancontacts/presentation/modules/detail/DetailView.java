package me.panavtec.cleancontacts.presentation.modules.detail;

import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.threaddecoratedview.views.qualifiers.NotDecorated;
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView;


@ThreadDecoratedView
public interface DetailView {

  @NotDecorated void initUi();

  void showContactData(PresentationContact contact);

  void showGetContactError();
}

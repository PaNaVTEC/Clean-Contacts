package me.panavtec.cleancontacts.presentation.modules.detail;

import me.panavtec.cleancontacts.presentation.PresenterView;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.presentation.common.views.qualifiers.ThreadDecoratedView;

@ThreadDecoratedView
public interface DetailView extends PresenterView {
  void showContactData(PresentationContact contact);

  void showGetContactError();
}

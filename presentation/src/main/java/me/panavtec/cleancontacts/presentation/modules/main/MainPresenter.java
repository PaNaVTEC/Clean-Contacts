package me.panavtec.cleancontacts.presentation.modules.main;

import java.util.List;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.presentation.Presenter;
import me.panavtec.presentation.common.outputs.InteractorOutput;
import me.panavtec.presentation.common.outputs.InteractorOutputInjector;
import me.panavtec.presentation.common.outputs.qualifiers.OnError;
import me.panavtec.presentation.common.outputs.qualifiers.OnResult;
import me.panavtec.presentation.common.outputs.qualifiers.Output;
import me.panavtec.threaddecoratedview.views.ThreadSpec;

public class MainPresenter extends Presenter<MainView> {
  private final InteractorInvoker interactorInvoker;
  private final GetContactsInteractor getContactsInteractor;
  private final ListMapper<Contact, PresentationContact> listMapper;

  @Output InteractorOutput<List<Contact>, RetrieveContactsException> output;

  public MainPresenter(InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor,
      final ListMapper<Contact, PresentationContact> listMapper, ThreadSpec mainThreadSpec) {
    super(mainThreadSpec);
    this.interactorInvoker = interactorInvoker;
    this.getContactsInteractor = getContactsInteractor;
    this.listMapper = listMapper;
    InteractorOutputInjector.inject(this);
  }

  @Override public void onViewAttached() {
    getView().initUi();
  }

  public void onResume() {
    refreshContactList();
  }

  public void onRefresh() {
    getView().refreshUi();
    refreshContactList();
  }

  private void refreshContactList() {
    interactorInvoker.execute(getContactsInteractor, output);
  }

  @OnResult void onContactsInteractor(List<Contact> result) {
    List<PresentationContact> presentationContacts = listMapper.modelToData(result);
    getView().refreshContactsList(presentationContacts);
  }

  @OnError void onContactsInteractorError(RetrieveContactsException data) {
    getView().showGetContactsError();
  }
}

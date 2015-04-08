package me.panavtec.cleancontacts.presentation.main;

import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.Action;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;
import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;

public class MainPresenter extends Presenter<MainView> {
  private final InteractorInvoker interactorInvoker;
  private final GetContactsInteractor getContactsInteractor;
  private final ListMapper<Contact, PresentationContact> listMapper;
  private final ThreadSpec mainThreadSpec;
  private final InteractorOutput<List<Contact>, RetrieveContactsException> getContactsListOutput;

  public MainPresenter(InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor,
      final ListMapper<Contact, PresentationContact> listMapper, ThreadSpec mainThreadSpec) {
    this.interactorInvoker = interactorInvoker;
    this.getContactsInteractor = getContactsInteractor;
    this.listMapper = listMapper;
    this.mainThreadSpec = mainThreadSpec;

    getContactsListOutput = new InteractorOutput.Builder<List<Contact>, RetrieveContactsException>(
        mainThreadSpec).onResult(new Action<List<Contact>>() {
      @Override public void onAction(List<Contact> data) {
        view.refreshContactsList(listMapper.modelToData(data));
      }
    }).onError(new Action<RetrieveContactsException>() {
      @Override public void onAction(RetrieveContactsException data) {
        view.showGetContactsError();
      }
    }).build();
  }

  @Override public void attachView(MainView view) {
    super.attachView(view);
    this.view.initUi();
  }

  public void onResume() {
  }

  public void onRefresh() {
    view.refreshUi();
    refreshContactList();
  }

  private void refreshContactList() {
    interactorInvoker.execute(getContactsInteractor, getContactsListOutput);
  }
}

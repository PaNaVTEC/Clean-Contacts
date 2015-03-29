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

public class MainPresenter extends Presenter {
  private final InteractorInvoker interactorInvoker;
  private final GetContactsInteractor getContactsInteractor;
  private final ListMapper<Contact, PresentationContact> listMapper;
  private final ThreadSpec mainThreadSpec;
  private final InteractorOutput<List<Contact>, RetrieveContactsException> getContactsListOutput;
  private MainView mainView;

  public MainPresenter(InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor, MainView mainView,
      final ListMapper<Contact, PresentationContact> listMapper, ThreadSpec mainThreadSpec) {
    this.interactorInvoker = interactorInvoker;
    this.getContactsInteractor = getContactsInteractor;
    this.mainView = mainView;
    this.listMapper = listMapper;
    this.mainThreadSpec = mainThreadSpec;
    
    getContactsListOutput = new InteractorOutput.Builder<List<Contact>, RetrieveContactsException>(
        mainThreadSpec).onResult(new Action<List<Contact>>() {
      @Override public void onAction(List<Contact> data) {
        MainPresenter.this.mainView.refreshContactsList(listMapper.modelToData(data));
      }
    }).onError(new Action<RetrieveContactsException>() {
      @Override public void onAction(RetrieveContactsException data) {
        MainPresenter.this.mainView.showGetContactsError();
      }
    }).build();
  }

  public void onCreate(MainView mainView) {
    this.mainView = mainView;
    this.mainView.initUi();
  }

  @Override public void onResume() {
    refreshContactList();
  }

  private void refreshContactList() {
    interactorInvoker.execute(getContactsInteractor, getContactsListOutput);
  }

  @Override public void onPause() {
  }

  public void onRefresh() {
    mainView.refreshUi();
    refreshContactList();
  }
}

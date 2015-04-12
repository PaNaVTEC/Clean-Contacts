package me.panavtec.cleancontacts.presentation.modules.main;

import java.util.List;
import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.presentation.outputs.Action;
import me.panavtec.cleancontacts.presentation.outputs.InteractorOutputImp;
import me.panavtec.cleancontacts.presentation.outputs.ThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.RetrieveContactsException;

public class MainPresenter extends Presenter<MainView> {
  private final InteractorInvoker interactorInvoker;
  private final GetContactsInteractor getContactsInteractor;
  private final ListMapper<Contact, PresentationContact> listMapper;
  private final ThreadSpec mainThreadSpec;
  private final InteractorOutputImp<List<Contact>, RetrieveContactsException> getContactsListOutput;

  public MainPresenter(InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor,
      final ListMapper<Contact, PresentationContact> listMapper, ThreadSpec mainThreadSpec) {
    this.interactorInvoker = interactorInvoker;
    this.getContactsInteractor = getContactsInteractor;
    this.listMapper = listMapper;
    this.mainThreadSpec = mainThreadSpec;

    getContactsListOutput = new InteractorOutputImp.Builder<List<Contact>, RetrieveContactsException>(
        mainThreadSpec).onResult(new Action<List<Contact>>() {
      @Override public void onAction(List<Contact> data) {
        getView().refreshContactsList(listMapper.modelToData(data));
      }
    }).onError(new Action<RetrieveContactsException>() {
      @Override public void onAction(RetrieveContactsException data) {
        getView().showGetContactsError();
      }
    }).build();
  }

  @Override public void attachView(MainView view) {
    super.attachView(view);
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
    interactorInvoker.execute(getContactsInteractor, getContactsListOutput);
  }
}

package me.panavtec.cleancontacts.presentation.main;

import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.events.GetContactsEvent;
import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;

public class MainPresenter extends Presenter {

  private final Bus bus;
  private final InteractorInvoker interactorInvoker;
  private final GetContactsInteractor getContactsInteractor;
  private final MainView mainView;
  private final ListMapper<Contact, PresentationContact> listMapper;

  public MainPresenter(Bus bus, InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor, MainView mainView,
      ListMapper<Contact, PresentationContact> listMapper) {
    this.bus = bus;
    this.interactorInvoker = interactorInvoker;
    this.getContactsInteractor = getContactsInteractor;
    this.mainView = mainView;
    this.listMapper = listMapper;
  }

  public void onCreate() {
    interactorInvoker.execute(getContactsInteractor);
  }

  @Override public void onResume() {
    bus.register(this);
  }

  @Override public void onPause() {
    bus.unregister(this);
  }

  public void onEvent(GetContactsEvent event) {
    if (event.getError() == null) {
      mainView.refreshContactsList(listMapper.modelToData(event.getContacts()));
    } else {
      mainView.showGetContactsError();
    }
  }

  public void onRefresh() {
    mainView.refreshUi();
    interactorInvoker.execute(getContactsInteractor);
  }
}

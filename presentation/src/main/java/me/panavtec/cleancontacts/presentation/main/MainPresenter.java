package me.panavtec.cleancontacts.presentation.main;

import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.events.GetContactsEvent;
import me.panavtec.cleancontacts.presentation.Presenter;

public class MainPresenter extends Presenter {

  private Bus bus;
  private InteractorInvoker interactorInvoker;
  private GetContactsInteractor getContactsInteractor;
  private MainView mainView;

  public MainPresenter(Bus bus, InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor, MainView mainView) {
    this.bus = bus;
    this.interactorInvoker = interactorInvoker;
    this.getContactsInteractor = getContactsInteractor;
    this.mainView = mainView;
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
      mainView.refreshContactsList(event.getContacts());
    } else {
      mainView.showGetContactsError();
    }
  }

  public void onRefresh() {
    mainView.refreshUi();
    interactorInvoker.execute(getContactsInteractor);
  }
}

package me.panavtec.cleancontacts.presentation.main;

import me.panavtec.cleancontacts.domain.EventBus;
import me.panavtec.cleancontacts.domain.interactors.InteractorExecutor;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.events.GetContactsEvent;
import me.panavtec.cleancontacts.presentation.Presenter;

public class MainPresenter extends Presenter {

    private EventBus bus;
    private InteractorExecutor interactorExecutor;
    private GetContactsInteractor getContactsInteractor;
    private MainView mainView;

    public MainPresenter(EventBus bus,
                         InteractorExecutor interactorExecutor,
                         GetContactsInteractor getContactsInteractor,
                         MainView mainView) {
        this.bus = bus;
        this.interactorExecutor = interactorExecutor;
        this.getContactsInteractor = getContactsInteractor;
        this.mainView = mainView;
    }

    public void onCreate() {
        interactorExecutor.execute(getContactsInteractor);
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
        interactorExecutor.execute(getContactsInteractor);
    }

}

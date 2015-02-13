package me.panavtec.cleancontacts.presentation.detail;

import me.panavtec.cleancontacts.domain.EventBus;
import me.panavtec.cleancontacts.domain.interactors.InteractorExecutor;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.events.GetContactEvent;
import me.panavtec.cleancontacts.presentation.Presenter;

public class DetailPresenter extends Presenter {

    private final EventBus bus;
    private final InteractorExecutor interactorExecutor;
    private final GetContactInteractor getContactInteractor;
    private final DetailView detailView;

    public DetailPresenter(EventBus bus,
                           InteractorExecutor interactorExecutor,
                           GetContactInteractor getContactInteractor,
                           DetailView detailView) {
        this.bus = bus;
        this.interactorExecutor = interactorExecutor;
        this.getContactInteractor = getContactInteractor;
        this.detailView = detailView;
    }


    @Override public void onResume() {
        bus.register(this);
    }

    @Override public void onPause() {
        bus.unregister(this);
    }

    public void onCreate(String contactMd5) {
        getContactInteractor.setData(contactMd5);
        interactorExecutor.execute(getContactInteractor);
    }

    public void onEvent(GetContactEvent event) {
        if (event.getError() == null) {
            detailView.showContactData(event.getContact());
        } else {
            detailView.showGetContactError();
        }
    }

}

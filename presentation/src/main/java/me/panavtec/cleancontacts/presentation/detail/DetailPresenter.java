package me.panavtec.cleancontacts.presentation.detail;

import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.events.GetContactEvent;
import me.panavtec.cleancontacts.presentation.Presenter;

public class DetailPresenter extends Presenter {

    private final Bus bus;
    private final InteractorInvoker interactorInvoker;
    private final GetContactInteractor getContactInteractor;
    private final DetailView detailView;

    public DetailPresenter(Bus bus,
                           InteractorInvoker interactorInvoker,
                           GetContactInteractor getContactInteractor,
                           DetailView detailView) {
        this.bus = bus;
        this.interactorInvoker = interactorInvoker;
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
        interactorInvoker.invoke(getContactInteractor);
    }

    public void onEvent(GetContactEvent event) {
        if (event.getError() == null) {
            detailView.showContactData(event.getContact());
        } else {
            detailView.showGetContactError();
        }
    }

}

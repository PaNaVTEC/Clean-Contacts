package me.panavtec.cleancontacts.presentation.detail;

import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.events.GetContactEvent;
import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;

public class DetailPresenter extends Presenter {

  private final Bus bus;
  private final InteractorInvoker interactorInvoker;
  private final GetContactInteractor getContactInteractor;
  private final PresentationContactMapper presentationContactMapper;
  private DetailView detailView;

  public DetailPresenter(Bus bus, InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor, DetailView detailView, PresentationContactMapper presentationContactMapper) {
    this.bus = bus;
    this.interactorInvoker = interactorInvoker;
    this.getContactInteractor = getContactInteractor;
    this.detailView = detailView;
    this.presentationContactMapper = presentationContactMapper;
  }

  public void onCreate(DetailView detailView) {
    this.detailView = detailView;
    detailView.initUi();
  }

  @Override public void onResume() {
    bus.register(this);
  }

  @Override public void onPause() {
    bus.unregister(this);
  }

  public void obtainContact(String contactMd5) {
    getContactInteractor.setData(contactMd5);
    interactorInvoker.execute(getContactInteractor);
  }

  public void onEvent(GetContactEvent event) {
    if (event.getError() == null) {
      detailView.showContactData(presentationContactMapper.modelToData(event.getContact()));
    } else {
      detailView.showGetContactError();
    }
  }
}

package me.panavtec.cleancontacts.presentation.detail;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.Action;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.CannotObtainContactException;
import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;

public class DetailPresenter extends Presenter {

  private final String contactMd5;
  private final InteractorInvoker interactorInvoker;
  private final GetContactInteractor getContactInteractor;
  private final PresentationContactMapper presentationContactMapper;
  private final ThreadSpec mainThreadSpec;
  private final InteractorOutput<Contact, CannotObtainContactException> getContactOutput;
  private DetailView detailView;

  public DetailPresenter(String contactMd5, InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor, DetailView detailView,
      final PresentationContactMapper presentationContactMapper, ThreadSpec mainThreadSpec) {
    this.contactMd5 = contactMd5;
    this.interactorInvoker = interactorInvoker;
    this.getContactInteractor = getContactInteractor;
    this.detailView = detailView;
    this.presentationContactMapper = presentationContactMapper;
    this.mainThreadSpec = mainThreadSpec;
    
    getContactOutput = new InteractorOutput.Builder<Contact, CannotObtainContactException>(
        mainThreadSpec).onResult(new Action<Contact>() {
      @Override public void onAction(Contact data) {
        DetailPresenter.this.detailView.showContactData(presentationContactMapper.modelToData(data));
      }
    }).onError(new Action<CannotObtainContactException>() {
      @Override public void onAction(CannotObtainContactException data) {
        DetailPresenter.this.detailView.showGetContactError();
      }
    }).build();
  }

  public void onCreate(DetailView detailView) {
    this.detailView = detailView;
    this.detailView.initUi();
  }

  @Override public void onResume() {
    obtainContact();
  }

  @Override public void onPause() {
  }

  public void obtainContact() {
    getContactInteractor.setData(contactMd5);
    interactorInvoker.execute(getContactInteractor, getContactOutput);
  }
}

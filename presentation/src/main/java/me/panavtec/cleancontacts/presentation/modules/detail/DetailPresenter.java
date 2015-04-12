package me.panavtec.cleancontacts.presentation.modules.detail;

import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.presentation.outputs.Action;
import me.panavtec.cleancontacts.presentation.outputs.InteractorOutputImp;
import me.panavtec.cleancontacts.presentation.outputs.ThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.exceptions.CannotObtainContactException;

public class DetailPresenter extends Presenter<DetailView> {

  private final String contactMd5;
  private final InteractorInvoker interactorInvoker;
  private final GetContactInteractor getContactInteractor;
  private final PresentationContactMapper presentationContactMapper;
  private final ThreadSpec mainThreadSpec;
  private final InteractorOutputImp<Contact, CannotObtainContactException> getContactOutput;

  public DetailPresenter(String contactMd5, InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor,
      final PresentationContactMapper presentationContactMapper, ThreadSpec mainThreadSpec) {
    this.contactMd5 = contactMd5;
    this.interactorInvoker = interactorInvoker;
    this.getContactInteractor = getContactInteractor;
    this.presentationContactMapper = presentationContactMapper;
    this.mainThreadSpec = mainThreadSpec;

    getContactOutput = new InteractorOutputImp.Builder<Contact, CannotObtainContactException>(
        mainThreadSpec).onResult(new Action<Contact>() {
      @Override public void onAction(Contact data) {
        getView().showContactData(presentationContactMapper.modelToData(data));
      }
    }).onError(new Action<CannotObtainContactException>() {
      @Override public void onAction(CannotObtainContactException data) {
        getView().showGetContactError();
      }
    }).build();
  }

  @Override public void attachView(DetailView view) {
    super.attachView(view);
    getView().initUi();
  }

  public void onResume() {
    obtainContact();
  }

  public void obtainContact() {
    getContactInteractor.setData(contactMd5);
    interactorInvoker.execute(getContactInteractor, getContactOutput);
  }
}

package me.panavtec.cleancontacts.presentation.modules.detail;

import me.panavtec.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.ObtainContactException;
import me.panavtec.presentation.common.outputs.InteractorOutput;
import me.panavtec.presentation.common.outputs.InteractorOutputInjector;
import me.panavtec.presentation.common.ThreadSpec;
import me.panavtec.presentation.common.outputs.qualifiers.OnError;
import me.panavtec.presentation.common.outputs.qualifiers.OnResult;
import me.panavtec.presentation.common.outputs.qualifiers.Output;

public class DetailPresenter extends Presenter<DetailView> {

  private final String contactMd5;
  private final InteractorInvoker interactorInvoker;
  private final GetContactInteractor getContactInteractor;
  private final PresentationContactMapper presentationContactMapper;
  @Output InteractorOutput<Contact, ObtainContactException> getContactOutput;

  public DetailPresenter(String contactMd5, InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor,
      final PresentationContactMapper presentationContactMapper, ThreadSpec mainThreadSpec) {
    super(mainThreadSpec);
    this.contactMd5 = contactMd5;
    this.interactorInvoker = interactorInvoker;
    this.getContactInteractor = getContactInteractor;
    this.presentationContactMapper = presentationContactMapper;
    InteractorOutputInjector.inject(this);
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

  @OnResult void onContactInteractor(Contact data) {
    getView().showContactData(presentationContactMapper.modelToData(data));
  }

  @OnError void onContactInteractorError(ObtainContactException e) {
    getView().showGetContactError();
  }
}

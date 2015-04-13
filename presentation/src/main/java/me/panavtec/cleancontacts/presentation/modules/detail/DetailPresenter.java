package me.panavtec.cleancontacts.presentation.modules.detail;

import java.util.concurrent.ExecutionException;
import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactInteractor;
import me.panavtec.presentation.common.InteractorOutput;
import me.panavtec.presentation.common.InteractorOutputInjector;
import me.panavtec.presentation.common.ThreadSpec;
import me.panavtec.presentation.common.qualifiers.OnError;
import me.panavtec.presentation.common.qualifiers.OnResult;
import me.panavtec.presentation.common.qualifiers.Output;

public class DetailPresenter extends Presenter<DetailView> {

  private final String contactMd5;
  private final InteractorInvoker interactorInvoker;
  private final GetContactInteractor getContactInteractor;
  private final PresentationContactMapper presentationContactMapper;
  @Output InteractorOutput<Contact> getContactOutput;

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

  @OnError void onContactInteractorError(ExecutionException e) {
    getView().showGetContactError();
  }
}

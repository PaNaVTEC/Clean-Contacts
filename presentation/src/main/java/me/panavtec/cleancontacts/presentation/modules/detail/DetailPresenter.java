package me.panavtec.cleancontacts.presentation.modules.detail;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.InteractorError;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactError;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.CleanContactsViewInjector;
import me.panavtec.cleancontacts.presentation.InteractorResult;
import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.invoker.InteractorExecution;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;

public class DetailPresenter extends Presenter<DetailView> {

  private final String contactMd5;
  private final InteractorInvoker interactorInvoker;
  private final GetContactInteractor getContactInteractor;
  private final PresentationContactMapper presentationContactMapper;

  public DetailPresenter(String contactMd5, InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor,
      PresentationContactMapper presentationContactMapper, CleanContactsViewInjector viewInjector) {
    super(viewInjector);
    this.contactMd5 = contactMd5;
    this.interactorInvoker = interactorInvoker;
    this.getContactInteractor = getContactInteractor;
    this.presentationContactMapper = presentationContactMapper;
  }

  @Override public void onViewAttached() {
    getView().initUi();
  }

  public void onResume() {
    obtainContact();
  }

  public void obtainContact() {
    getContactInteractor.setData(contactMd5);
    new InteractorExecution<>(getContactInteractor).result(new InteractorResult<Contact>() {
      @Override public void onResult(Contact result) {
        getView().showContactData(presentationContactMapper.modelToData(result));
      }
    }).error(GetContactError.class, new InteractorResult<InteractorError>() {
      @Override public void onResult(InteractorError result) {
        getView().showGetContactError();
      }
    }).execute(interactorInvoker);
  }
}

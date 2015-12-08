package me.panavtec.cleancontacts.presentation.modules.main;

import java.util.Collections;
import java.util.List;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactError;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.TestInteractorInvoker;
import me.panavtec.cleancontacts.presentation.interactors.GetContactsFailInteractor;
import me.panavtec.cleancontacts.presentation.interactors.GetContactsSuccessInteractor;
import me.panavtec.cleancontacts.presentation.interactors.base.TestThreadSpec;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.threaddecoratedview.views.ThreadSpec;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class MainPresenterShould {

  public static final InteractorResponse<List<Contact>> ERROR_RESPONSE =
      new InteractorResponse<>(new GetContactError());
  public static final InteractorResponse<List<Contact>> SUCCESS_RESPONSE =
      new InteractorResponse<>(Collections.singletonList(new Contact()));

  @Mock ListMapper<Contact, PresentationContact> listMapper;
  @Mock GetContactsInteractor getContactsInteractor;
  @Mock MainView view;

  private MainPresenter presenter;

  @Before public void setUp() {
    ThreadSpec threadSpec = new TestThreadSpec();
    InteractorInvoker interactorInvoker = TestInteractorInvoker.create();
    presenter = new MainPresenter(interactorInvoker, getContactsInteractor, listMapper, threadSpec);
    presenter.attachView(view);
  }

  @Test public void call_refresh_contact_list_when_refresh_contacts_list_success() {
    when(getContactsInteractor.call()).thenReturn(SUCCESS_RESPONSE);

    presenter.onResume();

    verify(view).refreshContactsList(anyListOf(PresentationContact.class));
  }

  @Test public void call_show_get_contacts_error_when_refresh_contacts_list_fails() {
    when(getContactsInteractor.call()).thenReturn(ERROR_RESPONSE);

    presenter.onResume();

    verify(view).showGetContactsError();
  }

  @Test public void onResumeRefreshViewWithContactsSuccess() {
    GetContactsSuccessInteractor getContactsSuccessInteractor = new GetContactsSuccessInteractor();
    MainView view = getView();
    MainPresenter presenter = initializePresenter(view, getContactsSuccessInteractor);
    presenter.onResume();
    verify(view).refreshContactsList(anyListOf(PresentationContact.class));
  }

  @Test public void onResumeRefreshViewWithContactsFail() {
    GetContactsFailInteractor getContactsFailInteractor = new GetContactsFailInteractor();
    MainView view = getView();
    MainPresenter presenter = initializePresenter(view, getContactsFailInteractor);
    presenter.onResume();
    verify(view).showGetContactsError();
  }

  private MainPresenter initializePresenter(MainView mainView,
      GetContactsInteractor getContactsInteractor) {
    MainPresenter presenter = this.presenter.get(getContactsInteractor);
    presenter.attachView(mainView);
    return presenter;
  }
}
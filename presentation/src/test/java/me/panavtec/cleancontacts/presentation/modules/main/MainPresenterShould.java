package me.panavtec.cleancontacts.presentation.modules.main;

import java.util.List;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsError;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.presentation.TestInteractorInvoker;
import me.panavtec.cleancontacts.presentation.TestViewInjector;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class MainPresenterShould {

  public static final InteractorResponse<List<Contact>> ERROR_RESPONSE =
      new InteractorResponse<>(new GetContactsError());
  public static final InteractorResponse<List<Contact>> SUCCESS_RESPONSE =
      new InteractorResponse<>(singletonList(new Contact()));

  @Mock ListMapper<Contact, PresentationContact> listMapper;
  @Mock GetContactsInteractor getContactsInteractor;
  @Mock MainView view;

  private MainPresenter presenter;

  @Before public void setUp() {
    presenter = new MainPresenter(TestInteractorInvoker.create(), getContactsInteractor, listMapper,
        new TestViewInjector());
    presenter.attachView(view);
  }

  @Test public void refresh_contact_list_when_refresh_contacts_list_success() {
    when(getContactsInteractor.call()).thenReturn(SUCCESS_RESPONSE);

    presenter.onResume();

    verify(view).refreshContactsList(anyListOf(PresentationContact.class));
  }

  @Test public void show_get_contacts_error_when_refresh_contacts_list_fails() {
    when(getContactsInteractor.call()).thenReturn(ERROR_RESPONSE);

    presenter.onResume();

    verify(view).showGetContactsError();
  }
}
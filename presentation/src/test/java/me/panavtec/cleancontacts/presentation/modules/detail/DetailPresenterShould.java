package me.panavtec.cleancontacts.presentation.modules.detail;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactError;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.TestInteractorInvoker;
import me.panavtec.cleancontacts.presentation.TestViewInjector;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) public class DetailPresenterShould {

  private static final String MD5 = "ContactMD5";
  private static final InteractorResponse<Contact> CONTACT_RESPONSE =
      new InteractorResponse<>(new Contact());
  private static final InteractorResponse<Contact> ERROR_RESPONSE =
      new InteractorResponse<>(new GetContactError());

  @Mock PresentationContactMapper mapper;
  @Mock GetContactInteractor getContactInteractor;
  @Mock DetailView view;

  private DetailPresenter presenter;

  @Before public void setUp() {
    presenter =
        new DetailPresenter(MD5, TestInteractorInvoker.create(), getContactInteractor, mapper,
            new TestViewInjector());
    presenter.attachView(view);
  }

  @Test public void show_contact_data_when_obtain_contact_success() {
    when(getContactInteractor.call()).thenReturn(CONTACT_RESPONSE);

    presenter.onResume();

    verify(view).showContactData(any(PresentationContact.class));
  }

  @Test public void show_get_contact_error_when_obtain_contact_fails() throws Exception {
    when(getContactInteractor.call()).thenReturn(ERROR_RESPONSE);

    presenter.onResume();

    verify(view).showGetContactError();
  }
}

package me.panavtec.cleancontacts.domain.modules.detail;

import me.panavtec.presentation.Presenter;
import me.panavtec.cleancontacts.domain.PresenterTest;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.GetContactFailinteractor;
import me.panavtec.cleancontacts.domain.interactors.GetContactSuccessInteractor;
import me.panavtec.cleancontacts.domain.interactors.base.TestInteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.TestThreadSpec;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.ObtainContactException;
import me.panavtec.cleancontacts.presentation.modules.detail.DetailPresenter;
import me.panavtec.cleancontacts.presentation.modules.detail.DetailView;
import me.panavtec.presentation.common.ThreadSpec;
import me.panavtec.presentation.common.outputs.InteractorOutput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class DetailPresenterTest extends PresenterTest<DetailView> {

  private static final String MD5 = "ContactMD5";
  private PresentationContactMapper mapper = new PresentationContactMapper();
  private InteractorInvoker interactorInvoker;
  private ThreadSpec threadSpec;
  private DetailPresenterFactory presenterFactory;
  private ArgumentMatcher<PresentationContact> md5Matcher =
      new ArgumentMatcher<PresentationContact>() {
        @Override public boolean matches(Object argument) {
          return MD5.equals(((PresentationContact) argument).getMd5());
        }
      };

  @Before public void setUp() {
    presenterFactory = new DetailPresenterFactory();
    threadSpec = new TestThreadSpec();
    interactorInvoker = spy(new TestInteractorInvoker());
    MockitoAnnotations.initMocks(this);
  }

  @Test public void onResume() {
    GetContactInteractor getContactInteractor = mock(GetContactInteractor.class);
    DetailPresenter presenter = initializePresenter(getView(), getContactInteractor);
    presenter.onResume();
    verify(interactorInvoker).execute(eq(getContactInteractor),
        Matchers.<InteractorOutput<Contact, ObtainContactException>>any());
  }

  @Test public void onResumeGetContactSuccess() {
    GetContactSuccessInteractor getContactSuccessInteractor = new GetContactSuccessInteractor();
    DetailView view = getView();
    DetailPresenter presenter = initializePresenter(view, getContactSuccessInteractor);
    presenter.onResume();
    verify(view).showContactData(any(PresentationContact.class));
  }

  @Test public void onResumeGetContactFail() {
    GetContactFailinteractor getContactFailInteractor = new GetContactFailinteractor();
    DetailView view = getView();
    DetailPresenter presenter = initializePresenter(view, getContactFailInteractor);
    presenter.onResume();
    verify(view).showGetContactError();
  }

  @Test public void onResumeCallsInteractorSetData() {
    GetContactInteractor getContactInteractor = mock(GetContactInteractor.class);
    DetailPresenter presenter = initializePresenter(getView(), getContactInteractor);
    presenter.onResume();
    verify(getContactInteractor).setData(MD5);
  }

  @Test public void onResumeGetContactResponse() {
    GetContactSuccessInteractor getContactSuccessInteractor = new GetContactSuccessInteractor();
    DetailView view = getView();
    DetailPresenter presenter = initializePresenter(view, getContactSuccessInteractor);
    presenter.onResume();
    verify(view).showContactData(argThat(md5Matcher));
  }

  private DetailPresenter initializePresenter(DetailView view,
      GetContactInteractor getContactInteractor) {
    DetailPresenter presenter = presenterFactory.get(getContactInteractor);
    presenter.attachView(view);
    return presenter;
  }

  @Override protected DetailView getView() {
    return mock(DetailView.class);
  }

  @Override protected Presenter<DetailView> getPresenter() {
    return presenterFactory.get(null);
  }

  class DetailPresenterFactory {

    DetailPresenter get(GetContactInteractor getContactInteractor) {
      return new DetailPresenter(MD5, interactorInvoker, getContactInteractor, mapper, threadSpec);
    }
  }
}

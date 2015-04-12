package me.panavtec.cleancontacts.presentation.modules.detail;

import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.PresenterTest;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.presentation.common.DecoratedInteractorOutput;
import me.panavtec.presentation.common.ThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.interactors.GetContactFailinteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.GetContactSuccessInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.base.TestInteractorInvoker;
import me.panavtec.cleancontacts.presentation.outputs.interactors.base.TestThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactInteractor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DetailPresenterTest extends PresenterTest<DetailView> {

  private static final String MD5 = "ContactMD5";
  private PresentationContactMapper mapper = new PresentationContactMapper();
  private TestInteractorInvoker interactorInvoker;
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
    interactorInvoker = Mockito.spy(new TestInteractorInvoker());
    MockitoAnnotations.initMocks(this);
  }

  @Test public void onResume() {
    GetContactInteractor getContactInteractor = Mockito.mock(GetContactInteractor.class);
    DetailPresenter presenter = initializePresenter(getContactInteractor);
    presenter.onResume();
    Mockito.verify(interactorInvoker)
        .execute(Mockito.eq(getContactInteractor), Mockito.any(DecoratedInteractorOutput.class));
  }

  @Test public void onResumeGetContactSuccess() {
    GetContactSuccessInteractor getContactSuccessInteractor = new GetContactSuccessInteractor();
    DetailPresenter presenter = initializePresenter(getContactSuccessInteractor);
    presenter.onResume();
    Mockito.verify(presenter.getView()).showContactData(Mockito.any(PresentationContact.class));
  }

  @Test public void onResumeGetContactFail() {
    GetContactFailinteractor getContactFailInteractor = new GetContactFailinteractor();
    DetailPresenter presenter = initializePresenter(getContactFailInteractor);
    presenter.onResume();
    Mockito.verify(presenter.getView()).showGetContactError();
  }

  @Test public void onResumeCallsInteractorSetData() {
    GetContactInteractor getContactInteractor = Mockito.mock(GetContactInteractor.class);
    DetailPresenter presenter = initializePresenter(getContactInteractor);
    presenter.onResume();
    Mockito.verify(getContactInteractor).setData(MD5);
  }

  @Test public void onResumeGetContactResponse() {
    GetContactSuccessInteractor getContactSuccessInteractor = new GetContactSuccessInteractor();
    DetailPresenter presenter = initializePresenter(getContactSuccessInteractor);
    presenter.onResume();
    Mockito.verify(presenter.getView()).showContactData(Mockito.argThat(md5Matcher));
  }

  private DetailPresenter initializePresenter(GetContactInteractor getContactInteractor) {
    DetailPresenter presenter = presenterFactory.get(getContactInteractor);
    presenter.attachView(getView());
    return presenter;
  }

  @Override protected DetailView getView() {
    return Mockito.mock(DetailView.class);
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

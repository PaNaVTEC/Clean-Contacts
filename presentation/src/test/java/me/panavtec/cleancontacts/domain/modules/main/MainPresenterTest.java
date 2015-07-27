package me.panavtec.cleancontacts.domain.modules.main;

import java.util.List;
import me.panavtec.presentation.Presenter;
import me.panavtec.cleancontacts.domain.PresenterTest;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.GetContactsFailInteractor;
import me.panavtec.cleancontacts.domain.interactors.GetContactsSuccessInteractor;
import me.panavtec.cleancontacts.domain.interactors.base.TestInteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.TestThreadSpec;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.exceptions.RetrieveContactsException;
import me.panavtec.cleancontacts.presentation.modules.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.modules.main.MainView;
import me.panavtec.presentation.common.ThreadSpec;
import me.panavtec.presentation.common.outputs.InteractorOutput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class MainPresenterTest extends PresenterTest<MainView> {

  @Mock ListMapper<Contact, PresentationContact> listMapper;
  private InteractorInvoker interactorInvoker;
  private ThreadSpec threadSpec;
  private MainPresenterFactory presenterFactory;

  @Before public void setUp() {
    super.setUp();
    presenterFactory = new MainPresenterFactory();
    threadSpec = new TestThreadSpec();
    interactorInvoker = spy(new TestInteractorInvoker());
    MockitoAnnotations.initMocks(this);
  }

  @Test public void onResume() {
    GetContactsInteractor getContactsInteractor = mock(GetContactsInteractor.class);
    MainPresenter presenter = initializePresenter(getView(), getContactsInteractor);
    presenter.onResume();
    verify(interactorInvoker).execute(eq(getContactsInteractor),
        Matchers.<InteractorOutput<List<Contact>, RetrieveContactsException>>any());
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
    MainPresenter presenter = presenterFactory.get(getContactsInteractor);
    presenter.attachView(mainView);
    return presenter;
  }

  @Override protected MainView getView() {
    return mock(MainView.class);
  }

  @Override protected Presenter<MainView> getPresenter() {
    return presenterFactory.get(null);
  }

  class MainPresenterFactory {

    MainPresenter get(GetContactsInteractor getContactsInteractor) {
      return new MainPresenter(interactorInvoker, getContactsInteractor, listMapper, threadSpec);
    }
  }
}
package me.panavtec.cleancontacts.presentation.modules.main;

import me.panavtec.cleancontacts.presentation.Presenter;
import me.panavtec.cleancontacts.presentation.PresenterTest;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.presentation.common.DecoratedInteractorOutput;
import me.panavtec.presentation.common.ThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.GetContactsFailInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.GetContactsSuccessInteractor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.base.TestInteractorInvoker;
import me.panavtec.cleancontacts.presentation.outputs.interactors.base.TestThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactsInteractor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MainPresenterTest extends PresenterTest<MainView> {

  @Mock ListMapper<Contact, PresentationContact> listMapper;
  private TestInteractorInvoker interactorInvoker;
  private ThreadSpec threadSpec;
  private MainPresenterFactory presenterFactory;

  @Before public void setUp() {
    presenterFactory = new MainPresenterFactory();
    threadSpec = new TestThreadSpec();
    interactorInvoker = Mockito.spy(new TestInteractorInvoker());
    MockitoAnnotations.initMocks(this);
  }

  @Test public void onResume() {
    GetContactsInteractor getContactsInteractor = Mockito.mock(GetContactsInteractor.class);
    MainPresenter presenter = initializePresenter(getContactsInteractor);
    presenter.onResume();
    Mockito.verify(interactorInvoker)
        .execute(Mockito.eq(getContactsInteractor), Mockito.any(DecoratedInteractorOutput.class));
  }

  @Test public void onResumeRefreshViewWithContactsSuccess() {
    GetContactsSuccessInteractor getContactsSuccessInteractor = new GetContactsSuccessInteractor();
    MainPresenter presenter = initializePresenter(getContactsSuccessInteractor);
    presenter.onResume();
    Mockito.verify(presenter.getView())
        .refreshContactsList(Mockito.anyListOf(PresentationContact.class));
  }

  @Test public void onResumeRefreshViewWithContactsFail() {
    GetContactsFailInteractor getContactsFailInteractor = new GetContactsFailInteractor();
    MainPresenter presenter = initializePresenter(getContactsFailInteractor);
    presenter.onResume();
    Mockito.verify(presenter.getView()).showGetContactsError();
  }

  private MainPresenter initializePresenter(GetContactsInteractor getContactsInteractor) {
    MainPresenter presenter = presenterFactory.get(getContactsInteractor);
    presenter.attachView(getView());
    return presenter;
  }

  @Override protected MainView getView() {
    return Mockito.mock(MainView.class);
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
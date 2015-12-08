package me.panavtec.cleancontacts.presentation;

import me.panavtec.cleancontacts.presentation.interactors.base.TestThreadSpec;
import me.panavtec.threaddecoratedview.views.ThreadSpec;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class PresenterShould {

  private TestThreadSpec threadSpec;
  private TestInteractorInvoker interactorInvoker;
  private View view;
  private DummyPresenter presenter;
  
  @Before public void setUp() {
    threadSpec = new TestThreadSpec();
    presenter = new DummyPresenter(threadSpec);
    MockitoAnnotations.initMocks(this);
  }

  @Test public void have_view_after_attach_view() {
    presenter.attachView(view);
    
    assertNotNull(presenter.getView());
  }

  @Test public void detachView() {
    Presenter<V> presenter = getPresenter();
    presenter.attachView(view);
    presenter.detachView();
    assertNotEquals(view, presenter.getView());
  }

  static class View {
    
  }
  
  static class DummyPresenter extends Presenter<View> {

    public DummyPresenter(ThreadSpec mainThreadSpec) {
      super(mainThreadSpec);
    }

    @Override public void onViewAttached() {
      
    }
  }
}


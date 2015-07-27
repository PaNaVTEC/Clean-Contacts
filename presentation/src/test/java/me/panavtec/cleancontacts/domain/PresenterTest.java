package me.panavtec.cleancontacts.domain;

import me.panavtec.cleancontacts.domain.interactors.base.TestInteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.TestThreadSpec;
import me.panavtec.presentation.Presenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public abstract class PresenterTest<V> {

  protected TestThreadSpec threadSpec;
  protected TestInteractorInvoker interactorInvoker;
  protected V view;

  @Before public void setUp() {
    threadSpec = new TestThreadSpec();
    interactorInvoker = new TestInteractorInvoker();
    view = getView();
    MockitoAnnotations.initMocks(this);
  }

  @Test public void attachView() {
    Presenter<V> presenter = getPresenter();
    presenter.attachView(view);
    assertNotNull(presenter.getView());
  }

  @Test public void detachView() {
    Presenter<V> presenter = getPresenter();
    presenter.attachView(view);
    presenter.detachView();
    assertNotEquals(view, presenter.getView());
  }

  protected abstract V getView();

  protected abstract Presenter<V> getPresenter();
}


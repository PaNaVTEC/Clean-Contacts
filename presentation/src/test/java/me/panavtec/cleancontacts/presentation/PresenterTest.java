package me.panavtec.cleancontacts.presentation;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public abstract class PresenterTest<V extends PresenterView> {

  @Test public void attachView() {
    Presenter<V> presenter = getPresenter();
    V view = getView();
    presenter.attachView(view);
    Mockito.verify(view).initUi();
  }
  
  @Test public void detachView() {
    Presenter<V> presenter = getPresenter();
    presenter.detachView();
    Assert.assertNull(presenter.getView());
  }

  protected abstract V getView();

  protected abstract Presenter<V> getPresenter();
}

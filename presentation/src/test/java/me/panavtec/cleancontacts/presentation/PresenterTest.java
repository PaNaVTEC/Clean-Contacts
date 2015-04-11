package me.panavtec.cleancontacts.presentation;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public abstract class PresenterTest<V extends PresenterView> {

  @Test public void attachView() {
    Presenter<V> presenter = getPresenter();
    presenter.attachView(getView());
    Mockito.verify(presenter.getView()).initUi();
  }

  @Test public void detachView() {
    Presenter<V> presenter = getPresenter();
    presenter.detachView();
    Assert.assertNull(presenter.getView());
  }

  protected abstract V getView();

  protected abstract Presenter<V> getPresenter();
}

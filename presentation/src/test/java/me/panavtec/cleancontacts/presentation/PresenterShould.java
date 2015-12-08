package me.panavtec.cleancontacts.presentation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PresenterShould {

  private SpyPresenter presenter;

  @Before public void setUp() {
    presenter = new SpyPresenter();
  }

  @Test public void call_on_view_attached_after_attaching_view() {
    presenter.attachView(new Object());

    assertTrue(presenter.onViewAttachedCalled);
  }

  static class SpyPresenter extends Presenter<Object> {

    public boolean onViewAttachedCalled;

    public SpyPresenter() {
      super(new TestViewInjector());
    }

    @Override public void onViewAttached() {
      onViewAttachedCalled = true;
    }
  }
}


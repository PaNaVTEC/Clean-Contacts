package me.panavtec.cleancontacts.presentation;

public abstract class Presenter<V extends PresenterView> {

  protected V view;

  public void attachView(V view) {
    this.view = view;
  }

  public void detachView() {
    this.view = null;
  }
}

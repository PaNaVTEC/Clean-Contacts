package me.panavtec.cleancontacts.presentation;

public abstract class Presenter<V> {

  private CleanContactsViewInjector viewInjector;
  private V view;

  public Presenter(CleanContactsViewInjector viewInjector) {
    this.viewInjector = viewInjector;
  }

  public void attachView(V view) {
    this.view = viewInjector.injectView(view);
    onViewAttached();
  }

  public void detachView() {
    this.view = viewInjector.nullObjectPatternView(view);
  }

  public V getView() {
    return view;
  }

  public abstract void onViewAttached();
}

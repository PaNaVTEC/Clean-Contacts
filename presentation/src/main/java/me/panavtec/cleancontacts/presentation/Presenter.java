package me.panavtec.cleancontacts.presentation;

import me.panavtec.threaddecoratedview.views.ThreadSpec;
import me.panavtec.threaddecoratedview.views.ViewInjector;

public abstract class Presenter<V> {

  private V view;
  private ThreadSpec mainThreadSpec;

  public Presenter(ThreadSpec mainThreadSpec) {
    this.mainThreadSpec = mainThreadSpec;
  }

  public void attachView(V view) {
    this.view = ViewInjector.inject(view, mainThreadSpec);
    onViewAttached();
  }

  public void detachView() {
    this.view = ViewInjector.nullObjectPatternView(view);
  }

  public V getView() {
    return view;
  }

  public abstract void onViewAttached();
}

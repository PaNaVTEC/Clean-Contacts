package me.panavtec.presentation;

import me.panavtec.presentation.common.ThreadSpec;
import me.panavtec.presentation.common.views.ViewInjector;

public abstract class Presenter<V> {

  private V view;
  private ThreadSpec mainThreadSpec;

  public Presenter(ThreadSpec mainThreadSpec) {
    this.mainThreadSpec = mainThreadSpec;
  }

  public void attachView(V view) {
    this.view = ViewInjector.inject(view, this, mainThreadSpec);
  }

  public void detachView() {
    view = null;
  }

  public V getView() {
    return view;
  }
}

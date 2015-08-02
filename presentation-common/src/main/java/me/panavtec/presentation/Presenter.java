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
    onViewAttached();
  }

  public void detachView() {
    view = ViewInjector.nullObjectPatternView(this);
  }

  public V getView() {
    return view;
  }

  public abstract void onViewAttached();
}

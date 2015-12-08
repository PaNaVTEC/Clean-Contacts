package me.panavtec.cleancontacts.presentation;

public class TestViewInjector implements CleanContactsViewInjector {
  @Override public <V> V injectView(V view) {
    return view;
  }

  @Override public <V> V nullObjectPatternView(V view) {
    return view;
  }
}

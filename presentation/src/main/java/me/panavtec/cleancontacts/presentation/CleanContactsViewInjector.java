package me.panavtec.cleancontacts.presentation;

public interface CleanContactsViewInjector {
  <V> V injectView(V view);
  <V> V nullObjectPatternView(V view);
}

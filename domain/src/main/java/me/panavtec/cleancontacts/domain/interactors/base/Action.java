package me.panavtec.cleancontacts.domain.interactors.base;

public interface Action<T> {
  public void onAction(T data);
}

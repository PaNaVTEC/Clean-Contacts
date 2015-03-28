package me.panavtec.cleancontacts.domain.interactors.base;

public abstract class Action<T> {
  public abstract void onAction(T data);
}

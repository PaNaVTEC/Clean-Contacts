package me.panavtec.cleancontacts.presentation.outputs;

public interface Action<T> {
  public void onAction(T data);
}

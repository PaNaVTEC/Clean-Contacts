package me.panavtec.presentation.common.outputs;

public interface Action<T> {
  public void onAction(T data);
}

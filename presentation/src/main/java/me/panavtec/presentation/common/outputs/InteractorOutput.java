package me.panavtec.presentation.common.outputs;

public interface InteractorOutput<T, E extends Exception> {
  void onResult(T result);

  void onError(E exception);

  void onCancel();
}

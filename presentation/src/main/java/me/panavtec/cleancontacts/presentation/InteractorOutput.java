package me.panavtec.cleancontacts.presentation;

public interface InteractorOutput<T, E extends Exception> {
  void onResult(T result);

  void onError(E exception);

  void onCancel();
}

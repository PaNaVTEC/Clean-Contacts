package me.panavtec.cleancontacts.presentation.outputs.interactors;

public interface InteractorOutput<T, E extends Exception> {
  void onResult(T result);

  void onError(E error);
}

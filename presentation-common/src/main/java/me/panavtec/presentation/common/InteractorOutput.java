package me.panavtec.presentation.common;

import java.util.concurrent.ExecutionException;

public interface InteractorOutput<T> {
  void onResult(T result);

  void onError(ExecutionException exception);

  void onCancel();
}

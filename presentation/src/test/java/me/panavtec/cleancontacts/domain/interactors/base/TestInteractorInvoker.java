package me.panavtec.cleancontacts.domain.interactors.base;

import java.util.concurrent.Future;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.presentation.InteractorResult;

public class TestInteractorInvoker implements InteractorInvoker {

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T> interactor) {
    return execute(interactor, null, 0);
  }

  @Override
  public <T, E extends Exception> Future<T> execute(Interactor<T> interactor, int priority) {
    return execute(interactor, null, priority);
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T> interactor,
      InteractorResult<T> output) {
    return execute(interactor, output, 0);
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T> interactor,
      InteractorResult<T> output, int priority) {
    try {
      output.onResult(interactor.call());
    } catch (Exception e) {
      output.onError((E) e);
    }
    return null;
  }
}
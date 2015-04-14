package me.panavtec.cleancontacts.presentation.outputs.interactors.base;

import java.util.concurrent.Future;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.outputs.interactors.Interactor;
import me.panavtec.presentation.common.outputs.InteractorOutput;

public class TestInteractorInvoker implements InteractorInvoker {

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor) {
    return execute(interactor, null, 0);
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output) {
    return execute(interactor, output, 0);
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, int priority) {
    try {
      output.onResult(interactor.call());
    } catch (Exception e) {
      output.onError((E) e);
    }
    return null;
  }
}

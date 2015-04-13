package me.panavtec.cleancontacts.presentation.invoker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import me.panavtec.cleancontacts.presentation.outputs.interactors.Interactor;
import me.panavtec.presentation.common.InteractorOutput;

public class InteractorInvokerImp implements InteractorInvoker {

  private ExecutorService executor;

  public InteractorInvokerImp(ExecutorService executor) {
    this.executor = executor;
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor) {
    return executor.submit(interactor);
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output) {
    return execute(interactor, output, 0);
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, int priority) {
    return (Future<T>) executor.submit(new InteractorOutputTask<>(interactor, output));
  }
}

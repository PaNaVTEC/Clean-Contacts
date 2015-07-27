package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.presentation.common.outputs.InteractorOutput;

public class InteractorInvokerImp implements InteractorInvoker {

  private ExecutorService executor;
  private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

  public InteractorInvokerImp(ExecutorService executor,
      Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
    this.executor = executor;
    this.uncaughtExceptionHandler = uncaughtExceptionHandler;
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor) {
    return execute(interactor, 0);
  }

  @Override
  public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor, int priority) {
    return executor.submit(new PriorityInteractorDecorator<>(interactor, priority));
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output) {
    return execute(interactor, output, 0);
  }

  @Override public <T, E extends Exception> Future<T> execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, int priority) {
    return (Future<T>) executor.submit(
        new InteractorOutputTask<>(interactor, priority, output, uncaughtExceptionHandler));
  }
}
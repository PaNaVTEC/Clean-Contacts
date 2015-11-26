package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.presentation.InteractorResult;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;

public class InteractorInvokerImp implements InteractorInvoker {

  private ExecutorService executor;
  private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

  public InteractorInvokerImp(ExecutorService executor,
      Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
    this.executor = executor;
    this.uncaughtExceptionHandler = uncaughtExceptionHandler;
  }

  @Override public <T extends InteractorResponse> Future<T> execute(Interactor<T> interactor) {
    return execute(interactor, 0);
  }

  @Override
  public <T extends InteractorResponse> Future<T> execute(Interactor<T> interactor, int priority) {
    return executor.submit(new PriorityInteractorDecorator<>(interactor, priority));
  }

  @Override public <R, T extends InteractorResponse> Future<T> execute(Interactor<T> interactor,
      InteractorResult<R> output) {
    return execute(interactor, output, 0);
  }

  @Override public <R, T extends InteractorResponse<R>> Future<T> execute(Interactor<T> interactor,
      InteractorResult<R> output, int priority) {
    return (Future<T>) executor.submit(
        new InteractorOutputTask<>(interactor, priority, output, uncaughtExceptionHandler));
  }
}
package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.presentation.invoker.InteractorExecution;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;

public class InteractorInvokerImp implements InteractorInvoker {

  private ExecutorService executor;
  private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

  public InteractorInvokerImp(ExecutorService executor,
      Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
    this.executor = executor;
    this.uncaughtExceptionHandler = uncaughtExceptionHandler;
  }

  @Override public <T> Future<T> execute(InteractorExecution<T> execution) {
    Interactor<InteractorResponse<T>> interactor = execution.getInteractor();
    if (execution.getInteractorResult() != null) {
      return (Future<T>) executor.submit(new InteractorExecutionFutureTask<>(execution, uncaughtExceptionHandler));
    } else {
      return (Future<T>) executor.submit(new PriorityInteractorDecorator<>(interactor, execution.getPriority()));
    }
  }
}
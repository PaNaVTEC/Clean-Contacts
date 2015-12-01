package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.presentation.invoker.InteractorExecution;

public class InteractorExecutionFutureTask<T> extends FutureTask<T> implements PriorizableInteractor {

  private final InteractorExecution<T> interactorExecution;
  private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
  private final String description;

  public InteractorExecutionFutureTask(InteractorExecution<T> interactorExecution,
      Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
    super((Callable<T>) interactorExecution.getInteractor());
    this.interactorExecution = interactorExecution;
    this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    this.description = interactorExecution.getInteractor().getClass().toString();
  }

  @Override protected void done() {
    super.done();
    try {
      InteractorResponse<T> response = (InteractorResponse<T>) get();
      if (response.hasError()) {
        throw new RuntimeException();
      } else {
        interactorExecution.getInteractorResult().onResult(response.getResult());
      }
    } catch (Exception e) {
      Throwable causeException = e.getCause();
      unhandledException(causeException != null ? causeException : e);
    }
  }

  private void unhandledException(Throwable cause) {
    UnhandledInteractorException e =
        new UnhandledInteractorException(description, cause.getClass().getName());
    e.initCause(cause);
    uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e);
  }

  public int getPriority() {
    return interactorExecution.getPriority();
  }

  @Override public String getDescription() {
    return description;
  }
}

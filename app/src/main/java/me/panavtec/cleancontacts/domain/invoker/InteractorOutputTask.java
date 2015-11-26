package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.presentation.InteractorResult;

public class InteractorOutputTask<R, T extends InteractorResponse<R>> extends FutureTask<T>
    implements PriorizableInteractor {

  private final InteractorResult<R> output;
  private final int priority;
  private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
  private final String description;

  public InteractorOutputTask(Callable<T> callable, int priority, InteractorResult<R> output,
      Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
    super(callable);
    this.output = output;
    this.priority = priority;
    this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    this.description = callable.getClass().toString();
  }

  @Override protected void done() {
    super.done();
    try {
      T response = get();
      if (response.hasError()) {
        throw new UnsupportedOperationException();
      } else {
        output.onResult(response.getResult());
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
    return priority;
  }

  @Override public String getDescription() {
    return description;
  }
}

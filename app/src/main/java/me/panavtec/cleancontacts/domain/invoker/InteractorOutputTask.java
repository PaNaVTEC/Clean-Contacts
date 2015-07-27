package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import me.panavtec.presentation.common.outputs.InteractorOutput;

public class InteractorOutputTask<T, E extends Exception> extends FutureTask<T>
    implements PriorizableInteractor {

  private final InteractorOutput<T, E> output;
  private final int priority;
  private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
  private final String description;

  public InteractorOutputTask(Callable<T> callable, int priority, InteractorOutput<T, E> output,
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
      output.onResult(get());
    } catch (InterruptedException e) {
      output.onCancel();
    } catch (ExecutionException e) {
      Throwable causeException = e.getCause();
      try {
        output.onError((E) causeException);
      } catch (ClassCastException classCastException) {
        unhandledException(causeException != null ? causeException : e);
      }
    } catch (Exception e) {
      unhandledException(e);
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

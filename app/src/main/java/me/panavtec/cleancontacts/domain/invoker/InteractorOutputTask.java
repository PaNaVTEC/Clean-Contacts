package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import me.panavtec.presentation.common.outputs.InteractorOutput;

public class InteractorOutputTask<T, E extends Exception> extends FutureTask<T> {

  private InteractorOutput<T, E> output;
  private int priority;

  public InteractorOutputTask(Callable<T> callable, int priority, InteractorOutput<T, E> output) {
    super(callable);
    this.output = output;
    this.priority = priority;
  }

  @Override protected void done() {
    super.done();
    try {
      output.onResult(get());
    } catch (InterruptedException e) {
      output.onCancel();
    } catch (ExecutionException e) {
      output.onError((E) e.getCause());
    }
  }

  public int getPriority() {
    return priority;
  }
}

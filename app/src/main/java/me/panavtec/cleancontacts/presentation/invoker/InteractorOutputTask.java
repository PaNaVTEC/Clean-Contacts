package me.panavtec.cleancontacts.presentation.invoker;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import me.panavtec.presentation.common.InteractorOutput;

public class InteractorOutputTask<T, E extends Exception> extends FutureTask<T> {

  private InteractorOutput<T, E> output;

  public InteractorOutputTask(Callable<T> callable, InteractorOutput<T, E> output) {
    super(callable);
    this.output = output;
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
}

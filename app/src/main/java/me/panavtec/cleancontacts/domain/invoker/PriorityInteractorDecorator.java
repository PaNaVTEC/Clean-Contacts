package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.Callable;
import me.panavtec.cleancontacts.presentation.invoker.InteractorExecution;

public class PriorityInteractorDecorator<T> implements Callable<T>, PriorizableInteractor {

  private InteractorExecution<T> execution;

  public PriorityInteractorDecorator(InteractorExecution<T> execution) {
    this.execution = execution;
  }

  @Override public T call() throws Exception {
    return (T) execution.getInteractor().call();
  }

  @Override public int getPriority() {
    return execution.getPriority();
  }

  @Override public String getDescription() {
    return execution.getClass().toString();
  }
}
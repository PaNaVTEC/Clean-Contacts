package me.panavtec.cleancontacts.domain.invoker;

import java.util.concurrent.Callable;
import me.panavtec.cleancontacts.domain.interactors.Interactor;

public class PriorityInteractorDecorator<T, E extends Exception>
    implements Callable<T>, PriorizableInteractor {

  private Interactor<T, E> interactor;
  private int priority;

  public PriorityInteractorDecorator(Interactor<T, E> interactor, int priority) {
    this.interactor = interactor;
    this.priority = priority;
  }

  @Override public T call() throws E {
    return interactor.call();
  }

  @Override public int getPriority() {
    return priority;
  }

  @Override public String getDescription() {
    return interactor.getClass().toString();
  }
}
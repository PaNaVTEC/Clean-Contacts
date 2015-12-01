package me.panavtec.cleancontacts.presentation.invoker;

import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorError;
import me.panavtec.cleancontacts.domain.interactors.InteractorResponse;
import me.panavtec.cleancontacts.presentation.InteractorResult;

public class InteractorExecution<T> {
  private InteractorResult<T> interactorResult;
  private InteractorResult<InteractorError> interactorError;
  private Interactor<InteractorResponse<T>> interactor;
  private int priority;

  public InteractorExecution(Interactor<InteractorResponse<T>> interactor) {
    this.interactor = interactor;
  }

  public InteractorExecution<T> result(InteractorResult<T> interactorResult) {
    this.interactorResult = interactorResult;
    return this;
  }

  public InteractorExecution<T> error(InteractorResult<InteractorError> interactorError) {
    this.interactorError = interactorError;
    return this;
  }

  public InteractorExecution<T> priority(int priority) {
    this.priority = priority;
    return this;
  }

  public Interactor<InteractorResponse<T>> getInteractor() {
    return interactor;
  }

  public InteractorResult<InteractorError> getInteractorError() {
    return interactorError;
  }

  public InteractorResult<T> getInteractorResult() {
    return interactorResult;
  }

  public void execute(InteractorInvoker interactorInvoker) {
    interactorInvoker.execute(this);
  }

  public int getPriority() {
    return priority;
  }
}

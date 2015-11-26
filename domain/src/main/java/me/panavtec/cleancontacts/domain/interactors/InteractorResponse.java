package me.panavtec.cleancontacts.domain.interactors;

public class InteractorResponse<T> {

  private InteractorError error;
  private T result;

  public InteractorResponse(InteractorError error) {
    this.error = error;
  }

  public InteractorResponse(T result) {
    this.result = result;
  }

  public InteractorError getError() {
    return error;
  }

  public T getResult() {
    return result;
  }

  public boolean hasError() {
    return error != null;
  }
}

package me.panavtec.cleancontacts.domain.invoker;

public class UnhandledInteractorException extends RuntimeException {
  public UnhandledInteractorException(String interactorName, String initiatorException) {
    super(String.format("Your interactor %s does not handle the exception: %s", interactorName,
        initiatorException));
  }
}

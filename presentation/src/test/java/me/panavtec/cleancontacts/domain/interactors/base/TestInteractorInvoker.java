package me.panavtec.cleancontacts.domain.interactors.base;

public class TestInteractorInvoker implements InteractorInvoker {

  @Override public <T, E extends Exception> void execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output) {
    execute(interactor, output, InteractorPriority.MEDIUM);
  }

  @Override public <T, E extends Exception> void execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, InteractorPriority priority) {
    interactor.execute(output);
  }
}

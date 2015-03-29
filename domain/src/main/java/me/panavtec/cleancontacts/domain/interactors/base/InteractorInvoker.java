package me.panavtec.cleancontacts.domain.interactors.base;

public interface InteractorInvoker {
  <T, E extends Exception> void execute(Interactor<T, E> interactor, InteractorOutput<T, E> output);

  <T, E extends Exception> void execute(Interactor<T, E> interactor, InteractorOutput<T, E> output,
      InteractorPriority priority);
}

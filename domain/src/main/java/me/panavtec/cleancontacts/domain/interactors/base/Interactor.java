package me.panavtec.cleancontacts.domain.interactors.base;

public interface Interactor<T, E extends Exception> {
  public void execute(InteractorOutput<T, E> output);
}

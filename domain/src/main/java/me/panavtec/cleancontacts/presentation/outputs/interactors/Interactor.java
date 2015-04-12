package me.panavtec.cleancontacts.presentation.outputs.interactors;

public interface Interactor<T, E extends Exception> {
  public void execute(InteractorOutput<T, E> output);
}

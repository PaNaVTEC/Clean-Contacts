package me.panavtec.cleancontacts.presentation.outputs.interactors;

import java.util.concurrent.Callable;

public interface Interactor<T, E extends Exception> extends Callable<T> {
  @Override T call() throws E;
}

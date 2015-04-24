package me.panavtec.cleancontacts.domain.interactors;

import java.util.concurrent.Callable;

public interface Interactor<T, E extends Exception> extends Callable<T> {
  @Override T call() throws E;
}

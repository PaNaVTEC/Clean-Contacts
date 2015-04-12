package me.panavtec.cleancontacts.presentation.outputs.interactors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public interface Interactor<T, E extends ExecutionException> extends Callable<T> {
  @Override T call() throws E;
}

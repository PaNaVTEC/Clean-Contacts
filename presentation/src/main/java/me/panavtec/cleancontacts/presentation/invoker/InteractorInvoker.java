package me.panavtec.cleancontacts.presentation.invoker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import me.panavtec.cleancontacts.presentation.outputs.interactors.Interactor;
import me.panavtec.presentation.common.InteractorOutput;

public interface InteractorInvoker {

  <T, E extends ExecutionException> Future<T> execute(Interactor<T, E> interactor);

  <T, E extends ExecutionException> Future<T> execute(Interactor<T, E> interactor,
      InteractorOutput<T> output);

  <T, E extends ExecutionException> Future<T> execute(Interactor<T, E> interactor,
      InteractorOutput<T> output, int priority);
}

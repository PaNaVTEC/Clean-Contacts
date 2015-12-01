package me.panavtec.cleancontacts.presentation.invoker;

import java.util.concurrent.Future;

public interface InteractorInvoker {
  <T> Future<T> execute(InteractorExecution<T> interactor);
}

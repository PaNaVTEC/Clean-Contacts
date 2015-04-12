package me.panavtec.cleancontacts.presentation.invoker;

import me.panavtec.cleancontacts.presentation.outputs.interactors.Interactor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.InteractorOutput;

public interface InteractorInvoker {
  <T, E extends Exception> void execute(Interactor<T, E> interactor, InteractorOutput<T, E> output);

  <T, E extends Exception> void execute(Interactor<T, E> interactor, InteractorOutput<T, E> output,
      int priority);
}

package me.panavtec.cleancontacts.presentation.outputs.interactors.base;

import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.outputs.interactors.Interactor;
import me.panavtec.presentation.common.InteractorOutput;

public class TestInteractorInvoker implements InteractorInvoker {

  @Override public <T, E extends Exception> void execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output) {
    execute(interactor, output, 100);
  }

  @Override public <T, E extends Exception> void execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, int priority) {
    interactor.execute(output);
  }
}

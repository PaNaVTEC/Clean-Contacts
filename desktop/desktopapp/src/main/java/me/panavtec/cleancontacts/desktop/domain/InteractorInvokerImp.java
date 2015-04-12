package me.panavtec.cleancontacts.desktop.domain;

import java.util.concurrent.ExecutorService;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.outputs.interactors.Interactor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.InteractorOutput;

public class InteractorInvokerImp implements InteractorInvoker {

  ExecutorService executor;

  public InteractorInvokerImp(ExecutorService executor) {
    this.executor = executor;
  }

  @Override public <T, E extends Exception> void execute(final Interactor<T, E> interactor,
      final InteractorOutput<T, E> output) {
    execute(interactor, output, 100);
  }

  @Override public <T, E extends Exception> void execute(final Interactor<T, E> interactor,
      final InteractorOutput<T, E> output, int priority) {
    executor.execute(new Runnable() {
      @Override public void run() {
        interactor.execute(output);
      }
    });
  }
}

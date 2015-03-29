package me.panavtec.cleancontacts.desktop.domain;

import java.util.concurrent.ExecutorService;
import me.panavtec.cleancontacts.domain.interactors.base.Interactor;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorPriority;

public class InteractorInvokerImp implements InteractorInvoker {

  ExecutorService executor;

  public InteractorInvokerImp(ExecutorService executor) {
    this.executor = executor;
  }

  @Override public <T, E extends Exception> void execute(final Interactor<T, E> interactor,
      final InteractorOutput<T, E> output) {
    execute(interactor, output, InteractorPriority.MEDIUM);
  }

  @Override public <T, E extends Exception> void execute(final Interactor<T, E> interactor,
      final InteractorOutput<T, E> output, InteractorPriority priority) {
    executor.execute(new Runnable() {
      @Override public void run() {
        interactor.execute(output);
      }
    });
  }
}

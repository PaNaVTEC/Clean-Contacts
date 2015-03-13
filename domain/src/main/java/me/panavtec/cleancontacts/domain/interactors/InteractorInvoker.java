package me.panavtec.cleancontacts.domain.interactors;

public interface InteractorInvoker {
  void execute(Interactor interactor);

  void execute(Interactor interactor, InteractorPriority priority);
}

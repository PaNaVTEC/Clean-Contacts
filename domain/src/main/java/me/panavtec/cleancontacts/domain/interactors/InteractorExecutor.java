package me.panavtec.cleancontacts.domain.interactors;

public interface InteractorExecutor {
    void execute(Interactor interactor);
    void execute(Interactor interactor, InteractorPriority priority);
}

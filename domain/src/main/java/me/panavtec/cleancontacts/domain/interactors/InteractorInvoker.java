package me.panavtec.cleancontacts.domain.interactors;

public interface InteractorInvoker {
    void invoke(Interactor interactor);
    void invoke(Interactor interactor, InteractorPriority priority);
}

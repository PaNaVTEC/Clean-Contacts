package me.panavtec.cleancontacts.desktop.domain;

import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.InteractorPriority;

import java.util.concurrent.ExecutorService;

public class InteractorInvokerImp implements InteractorInvoker {

    ExecutorService executor;

    public InteractorInvokerImp(ExecutorService executor) {
        this.executor = executor;
    }

    @Override public void invoke(final Interactor interactor) {
        executor.execute(new Runnable() {
            @Override public void run() {
                interactor.execute();
            }
        });
    }

    @Override public void invoke(Interactor interactor, InteractorPriority priority) {
        invoke(interactor);
    }
}

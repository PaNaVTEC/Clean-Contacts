package me.panavtec.cleancontacts.domain;

import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.InteractorPriority;

public class InteractorExecutorImp implements InteractorInvoker {

    
    
    @Override public void invoke(Interactor interactor) {
        interactor.execute();
    }

    @Override public void invoke(Interactor interactor, InteractorPriority priority) {


    }
}

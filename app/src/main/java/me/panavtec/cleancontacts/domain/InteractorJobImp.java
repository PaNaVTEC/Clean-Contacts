package me.panavtec.cleancontacts.domain;

import me.panavtec.cleancontacts.domain.interactors.Interactor;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

public class InteractorJobImp extends Job {

    private EventBus bus;
    private Interactor interactor;

    public InteractorJobImp(Params params, EventBus bus, Interactor interactor) {
        super(params);
        this.bus = bus;
        this.interactor = interactor;
    }

    @Override public void onAdded() {
    }

    @Override public void onRun() throws Throwable {
        interactor.execute();
    }

    @Override protected void onCancel() {
    }

    @Override protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }

}

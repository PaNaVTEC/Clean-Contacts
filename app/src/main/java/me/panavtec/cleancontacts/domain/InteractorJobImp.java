package me.panavtec.cleancontacts.domain;

import me.panavtec.cleancontacts.domain.interactors.Interactor;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

public class InteractorJobImp extends Job {

    private Interactor interactor;

    public InteractorJobImp(Params params, Interactor interactor) {
        super(params);
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

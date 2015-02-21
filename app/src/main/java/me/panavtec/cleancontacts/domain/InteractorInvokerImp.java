package me.panavtec.cleancontacts.domain;

import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.InteractorPriority;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

public class InteractorInvokerImp implements InteractorInvoker {

    private JobManager jobManager;

    public InteractorInvokerImp(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    @Override public void invoke(Interactor interactor) {
        execute(interactor, InteractorPriority.MEDIUM);
    }

    @Override public void execute(Interactor interactor, InteractorPriority priority) {
        jobManager.addJob(interactorToJob(interactor, priority));
    }

    private Job interactorToJob(Interactor interactor, InteractorPriority priority) {
        Params params = new Params(priority.getPriorityValue());
        return new InteractorJobImp(params, interactor);
    }

}

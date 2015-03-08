package me.panavtec.cleancontacts.domain;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import me.panavtec.cleancontacts.domain.interactors.Interactor;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.InteractorPriority;

public class InteractorInvokerImp implements InteractorInvoker {

  private JobManager jobManager;

  public InteractorInvokerImp(JobManager jobManager) {
    this.jobManager = jobManager;
  }

  @Override public void execute(Interactor interactor) {
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

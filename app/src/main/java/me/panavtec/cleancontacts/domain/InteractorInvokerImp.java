package me.panavtec.cleancontacts.domain;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import me.panavtec.cleancontacts.domain.interactors.base.Interactor;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorPriority;

public class InteractorInvokerImp implements InteractorInvoker {

  private JobManager jobManager;

  public InteractorInvokerImp(JobManager jobManager) {
    this.jobManager = jobManager;
  }

  @Override public <T, E extends Exception> void execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output) {
    execute(interactor, output, InteractorPriority.MEDIUM);
  }

  @Override public <T, E extends Exception> void execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, InteractorPriority priority) {
    jobManager.addJob(interactorToJob(interactor, output, priority));
  }

  private <T, E extends Exception> Job interactorToJob(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, InteractorPriority priority) {
    Params params = new Params(priority.getPriorityValue());
    return new InteractorJobImp<>(params, output, interactor);
  }
}

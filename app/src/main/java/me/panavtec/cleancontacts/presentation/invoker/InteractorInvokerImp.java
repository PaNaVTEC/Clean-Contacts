package me.panavtec.cleancontacts.presentation.invoker;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import me.panavtec.cleancontacts.presentation.outputs.interactors.Interactor;
import me.panavtec.cleancontacts.presentation.outputs.interactors.InteractorOutput;

public class InteractorInvokerImp implements InteractorInvoker {

  private JobManager jobManager;

  public InteractorInvokerImp(JobManager jobManager) {
    this.jobManager = jobManager;
  }

  @Override public <T, E extends Exception> void execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output) {
    execute(interactor, output, InteractorPriorityImp.MEDIUM.getPriorityValue());
  }

  @Override public <T, E extends Exception> void execute(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, int priority) {
    jobManager.addJob(interactorToJob(interactor, output, priority));
  }

  private <T, E extends Exception> Job interactorToJob(Interactor<T, E> interactor,
      InteractorOutput<T, E> output, int priority) {
    Params params = new Params(priority);
    return new InteractorJobImp<>(params, output, interactor);
  }
}

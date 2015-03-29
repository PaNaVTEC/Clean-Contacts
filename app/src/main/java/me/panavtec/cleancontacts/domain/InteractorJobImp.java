package me.panavtec.cleancontacts.domain;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import me.panavtec.cleancontacts.domain.interactors.base.Interactor;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorOutput;

public class InteractorJobImp<T, E extends Exception> extends Job {

  private final InteractorOutput<T, E> output;
  private final Interactor<T, E> interactor;

  public InteractorJobImp(Params params, InteractorOutput<T, E> output,
      Interactor<T, E> interactor) {
    super(params);
    this.output = output;
    this.interactor = interactor;
  }

  @Override public void onAdded() {
  }

  @Override public void onRun() throws Throwable {
    interactor.execute(output);
  }

  @Override protected void onCancel() {
  }

  @Override protected boolean shouldReRunOnThrowable(Throwable throwable) {
    return false;
  }
}

package me.panavtec.cleancontacts.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public abstract class BaseActivity extends ActionBarActivity {

  private ActivityInjector activityInjector;
  private ActivityViewInjector viewInjector = new ActivityViewInjector();

  @Override protected void onCreate(Bundle savedInstanceState) {
    createActivityModule();
    super.onCreate(savedInstanceState);
    viewInjector.inject(this, onCreateViewId());
  }

  public int onCreateViewId() {
    return 0;
  }

  private void createActivityModule() {
    activityInjector = (ActivityInjector) getLastCustomNonConfigurationInstance();
    if (activityInjector == null) {
      activityInjector = new ActivityInjector();
      activityInjector.createGraph(this, newDiModule());
    }
    activityInjector.inject(this);
  }

  @Override public Object onRetainCustomNonConfigurationInstance() {
    return activityInjector;
  }

  protected abstract Object newDiModule();
}

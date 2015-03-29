package me.panavtec.cleancontacts.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import me.panavtec.cleancontacts.ui.configuration.ConfigurationKeeper;
import me.panavtec.cleancontacts.ui.configuration.ConfigurationKeeperListener;

public abstract class BaseActivity<T> extends ActionBarActivity
    implements ConfigurationKeeperListener {

  private ActivityInjector activityInjector;
  private ActivityViewInjector viewInjector = new ActivityViewInjector();
  private ConfigurationKeeper configurationKeeper = new ConfigurationKeeper(this);

  @Override protected void onCreate(Bundle savedInstanceState) {
    createActivityModule();
    super.onCreate(savedInstanceState);
    viewInjector.inject(this, onCreateViewId());
    configurationKeeper.create();
  }

  public int onCreateViewId() {
    return 0;
  }

  @Override protected void onDestroy() {
    configurationKeeper.destroy();
    super.onDestroy();
  }

  @Override public void onDestroyConfigurationKeeper() {
    activityInjector = null;
    configurationKeeper = null;
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

  protected abstract T newDiModule();
}

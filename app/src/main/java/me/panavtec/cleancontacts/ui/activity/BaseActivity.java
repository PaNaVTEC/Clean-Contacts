package me.panavtec.cleancontacts.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import me.panavtec.cleancontacts.ui.configuration.ConfigurationKeeper;
import me.panavtec.cleancontacts.ui.configuration.ConfigurationKeeperListener;

public abstract class BaseActivity<T> extends ActionBarActivity
    implements ConfigurationKeeperListener {

  private ActivityInjector activityInjector;
  private ConfigurationKeeper configurationKeeper = new ConfigurationKeeper(this);

  @DebugLog @Override protected void onCreate(Bundle savedInstanceState) {
    createActivityModule();
    super.onCreate(savedInstanceState);
    injectView();
    configurationKeeper.create();
  }

  private void injectView() {
    int layoutId = onCreateViewId();
    if (layoutId != 0) {
      setContentView(layoutId);
      ButterKnife.inject(this);
    }
  }

  public int onCreateViewId() {
    return 0;
  }

  @DebugLog @Override protected void onDestroy() {
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

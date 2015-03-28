package me.panavtec.cleancontacts.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import dagger.ObjectGraph;
import hugo.weaving.DebugLog;
import me.panavtec.cleancontacts.ui.configuration.ConfigurationKeeper;
import me.panavtec.cleancontacts.ui.configuration.ConfigurationKeeperListener;

public abstract class BaseActivity<T> extends ActionBarActivity
    implements ConfigurationKeeperListener {

  private ObjectGraph activityGraph;
  ConfigurationKeeper configurationKeeper = new ConfigurationKeeper(this);

  @Override public void onDestroyConfigurationKeeper() {
    activityGraph = null;
    configurationKeeper = null;
  }

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

  private void createActivityModule() {
    activityGraph = (ObjectGraph) getLastCustomNonConfigurationInstance();
    if (activityGraph == null) {
      activityGraph = new ActivityInjector(this).createGraph(newDiModule());
    }
    activityGraph.inject(this);
  }

  @Override public Object onRetainCustomNonConfigurationInstance() {
    return activityGraph;
  }

  protected abstract T newDiModule();

}

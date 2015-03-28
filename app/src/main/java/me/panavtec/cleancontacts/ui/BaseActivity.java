package me.panavtec.cleancontacts.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import butterknife.ButterKnife;
import dagger.ObjectGraph;
import hugo.weaving.DebugLog;
import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.CleanContactsApp;
import me.panavtec.cleancontacts.di.ActivityModule;

public abstract class BaseActivity<T> extends ActionBarActivity
    implements ConfigurationHandler.ConfigurationHandlerListener {

  private ObjectGraph activityGraph;
  private T diModule;

  ConfigurationHandler configurationHandler = new ConfigurationHandler(this);

  @Override public void destroyThemAll() {
    activityGraph = null;
  }
  
  @DebugLog @Override protected void onStart() {
    super.onStart();
  }

  @DebugLog @Override protected void onStop() {
    super.onStop();
  }

  @DebugLog @Override protected void onCreate(Bundle savedInstanceState) {
    createActivityModule();
    super.onCreate(savedInstanceState);
    injectView();
    configurationHandler.create();
  }

  @DebugLog @Override protected void onResume() {
    super.onResume();
  }

  @DebugLog @Override protected void onPause() {
    super.onPause();
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
    configurationHandler.destroy();
    super.onDestroy();
  }

  public ObjectGraph getActivityGraph() {
    return activityGraph;
  }

  private void createActivityModule() {
    Object savedObjectGraph = getLastCustomNonConfigurationInstance();
    Log.d("MainActivity", "graph: " + savedObjectGraph);
    if (savedObjectGraph == null) {
      diModule = newDiModule();
      activityGraph = new ActivityInjector(this).createGraph(diModule);
    } else {
      activityGraph = (ObjectGraph) savedObjectGraph;
      activityGraph.inject(this);
    }
  }

  @Override public Object onRetainCustomNonConfigurationInstance() {
    return activityGraph;
  }

  protected abstract T newDiModule();

  static class ActivityInjector {

    private ActionBarActivity activity;

    public ActivityInjector(ActionBarActivity activity) {
      this.activity = activity;
    }

    public ObjectGraph createGraph(Object module) {
      ArrayList<Object> modules = new ArrayList<>();
      modules.add(module);
      return createGraph(modules);
    }

    public ObjectGraph createGraph(List<Object> modules) {
      CleanContactsApp app = CleanContactsApp.get(activity);
      ObjectGraph graph = app.getObjectGraph().plus(getCombinedModules(modules).toArray());
      graph.inject(activity);
      return graph;
    }

    private List<Object> getCombinedModules(List<Object> modules) {
      List<Object> combined = new ArrayList<>(modules);
      combined.add(new ActivityModule(activity));
      return combined;
    }
  }
}

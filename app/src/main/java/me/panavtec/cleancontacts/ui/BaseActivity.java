package me.panavtec.cleancontacts.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import dagger.ObjectGraph;
import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.CleanContactsApp;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.di.LollipopModule;
import me.panavtec.cleancontacts.di.PreLollipopModule;

public class BaseActivity extends ActionBarActivity {

  private ObjectGraph activityGraph;

  @Override protected void onCreate(Bundle savedInstanceState) {
    activityGraph = new ActivityInjector(this).createGraph(getModules());
    super.onCreate(savedInstanceState);

    int layoutId = onCreateViewId();
    if (layoutId != 0) {
      setContentView(layoutId);
      ButterKnife.inject(this);
    }
  }

  public int onCreateViewId() {
    return 0;
  }

  @Override protected void onDestroy() {
    activityGraph = null;
    super.onDestroy();
  }

  public ObjectGraph getActivityGraph() {
    return activityGraph;
  }

  public void inject(Object obj) {
    if (activityGraph != null) {
      activityGraph.inject(obj);
    }
  }

  protected List<Object> getModules() {
    return new ArrayList<>();
  }

  static class ActivityInjector {

    private ActionBarActivity activity;

    public ActivityInjector(ActionBarActivity activity) {
      this.activity = activity;
    }

    public ObjectGraph createGraph() {
      return createGraph(new ArrayList<>());
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
      combined.add(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? new LollipopModule()
          : new PreLollipopModule());
      return combined;
    }
  }
}

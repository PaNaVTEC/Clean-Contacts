package me.panavtec.cleancontacts.ui;

import android.support.v7.app.ActionBarActivity;
import dagger.ObjectGraph;
import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.CleanContactsApp;
import me.panavtec.cleancontacts.di.ActivityModule;

public class ActivityInjector {

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
    return app.getObjectGraph().plus(getCombinedModules(modules).toArray());
  }

  private List<Object> getCombinedModules(List<Object> modules) {
    List<Object> combined = new ArrayList<>(modules);
    combined.add(new ActivityModule(activity));
    return combined;
  }
  
}

package me.panavtec.cleancontacts.ui.activity;

import android.support.v7.app.AppCompatActivity;
import dagger.ObjectGraph;
import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.CleanContactsApp;
import me.panavtec.cleancontacts.di.ActivityModule;

public class ActivityInjector {

  private ObjectGraph objectGraph;

  public void createGraph(AppCompatActivity activity, Object module) {
    ArrayList<Object> modules = new ArrayList<>();
    modules.add(module);
    createGraph(activity, modules);
  }

  public void createGraph(AppCompatActivity activity, List<Object> modules) {
    CleanContactsApp app = CleanContactsApp.get(activity);
    objectGraph = app.getObjectGraph().plus(getCombinedModules(activity, modules).toArray());
  }

  private List<Object> getCombinedModules(AppCompatActivity activity, List<Object> modules) {
    List<Object> combined = new ArrayList<>(modules);
    combined.add(new ActivityModule(activity));
    return combined;
  }

  public void inject(Object object) {
    objectGraph.inject(object);
  }
}

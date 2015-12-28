package me.panavtec.cleancontacts;

import android.app.Application;
import android.content.Context;
import dagger.ObjectGraph;
import me.panavtec.cleancontacts.di.AppModule;

public class CleanContactsApp extends Application {

  private ObjectGraph objectGraph;

  @Override public void onCreate() {
    super.onCreate();
    initObjectGraph();
  }

  private void initObjectGraph() {
    objectGraph = ObjectGraph.create(new AppModule(this));
    inject(this);
  }

  public void inject(Object object) {
    objectGraph.inject(object);
  }

  public static CleanContactsApp get(Context context) {
    return (CleanContactsApp) context.getApplicationContext();
  }

  public ObjectGraph getObjectGraph() {
    return objectGraph;
  }

  public void setObjectGraph(ObjectGraph objectGraph) {
    this.objectGraph = objectGraph;
  }
}

package me.panavtec.cleancontacts;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import dagger.ObjectGraph;
import me.panavtec.cleancontacts.data.ApiModuleMock;
import me.panavtec.cleancontacts.data.BddModuleMock;
import me.panavtec.cleancontacts.di.AppModule;

public class DaggerActivityTestRule<T extends Activity> extends ActivityTestRule<T> {

  public DaggerActivityTestRule(Class<T> activityClass) {
    super(activityClass);
  }

  public DaggerActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
    super(activityClass, initialTouchMode);
  }

  public DaggerActivityTestRule(Class<T> activityClass, boolean initialTouchMode,
      boolean launchActivity) {
    super(activityClass, initialTouchMode, launchActivity);
  }

  @Override protected void beforeActivityLaunched() {
    super.beforeActivityLaunched();
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    CleanContactsApp app = (CleanContactsApp) instrumentation.getTargetContext()
        .getApplicationContext();
    app.setObjectGraph(ObjectGraph.create(new AppModule(app), new ApiModuleMock(), new BddModuleMock()));
  }
}

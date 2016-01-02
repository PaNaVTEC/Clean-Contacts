package me.panavtec.cleancontacts;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.test.espresso.IdlingResource;
import java.util.concurrent.atomic.AtomicBoolean;

public class ActivityResumedIdlingResource implements Application.ActivityLifecycleCallbacks, IdlingResource {

  private static final AtomicBoolean isIdle = new AtomicBoolean(false);
  private IdlingResource.ResourceCallback callback;

  public ActivityResumedIdlingResource(Application app) {
    app.registerActivityLifecycleCallbacks(this);
  }

  @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
  }

  @Override public void onActivityStarted(Activity activity) {
  }

  @Override public void onActivityResumed(Activity activity) {
    isIdle.set(true);
  }

  @Override public void onActivityPaused(Activity activity) {
  }

  @Override public void onActivityStopped(Activity activity) {
  }

  @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
  }

  @Override public void onActivityDestroyed(Activity activity) {
  }

  @Override public String getName() {
    return "Activity is unresumed";
  }

  @Override public boolean isIdleNow() {
    if (isIdle.get() && callback != null) {
      callback.onTransitionToIdle();
    }
    return isIdle.get();
  }

  @Override public void registerIdleTransitionCallback(IdlingResource.ResourceCallback callback) {
    this.callback = callback;
    if (isIdle.get()) {
      callback.onTransitionToIdle();
    }
  }

  public void unregister(Application app) {
    app.unregisterActivityLifecycleCallbacks(this);
  }
}

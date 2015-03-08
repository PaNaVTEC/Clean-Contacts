package me.panavtec.cleancontacts.di;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.ui.elevation.ElevationHandler;
import me.panavtec.cleancontacts.ui.elevation.LollipopElevationHandler;
import me.panavtec.cleancontacts.ui.transitions.LollipopWindowTransitionListener;
import me.panavtec.cleancontacts.ui.transitions.WindowTransitionListener;

@Module(
    addsTo = ActivityModule.class,
    complete = false,
    library = true)
public class LollipopModule {

  @Provides ElevationHandler provideElevationHandler(Context context) {
    return new LollipopElevationHandler(context);
  }

  @Provides WindowTransitionListener provideWindowTransitionListener(ActionBarActivity activity,
      WindowTransitionListener.WindowTransitionEndListener endListener) {
    return new LollipopWindowTransitionListener(endListener, activity.getWindow());
  }
}

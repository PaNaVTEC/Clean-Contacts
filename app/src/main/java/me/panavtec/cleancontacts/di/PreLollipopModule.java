package me.panavtec.cleancontacts.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.ui.elevation.ElevationHandler;
import me.panavtec.cleancontacts.ui.elevation.NoElevationHandler;
import me.panavtec.cleancontacts.ui.transitions.NoWindowTransitionListener;
import me.panavtec.cleancontacts.ui.transitions.WindowTransitionListener;

@Module(
    addsTo = ActivityModule.class,
    complete = false,
    library = true)
public class PreLollipopModule {

  @Provides ElevationHandler provideElevationHandler(Context context) {
    return new NoElevationHandler(context);
  }

  @Provides WindowTransitionListener provideWindowTransitionListener(
      WindowTransitionListener.WindowTransitionEndListener endListener) {
    return new NoWindowTransitionListener(endListener);
  }
}
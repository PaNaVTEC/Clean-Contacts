package me.panavtec.cleancontacts.di;

import android.content.Context;
import android.view.Window;
import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.ui.elevation.ElevationHandler;
import me.panavtec.cleancontacts.ui.elevation.LollipopElevationHandler;
import me.panavtec.cleancontacts.ui.transitions.LollipopWindowTransitionListener;
import me.panavtec.cleancontacts.ui.transitions.WindowTransitionListener;

@Module(
    library = true,
    complete = false,
    overrides = true)
public class LollipopModule {

  private Window window;

  public LollipopModule(Window window) {
    this.window = window;
  }
 
  @Provides ElevationHandler provideElevationHandler(Context context) {
    return new LollipopElevationHandler(context);
  }

  @Provides WindowTransitionListener provideWindowTransitionListener(
      WindowTransitionListener.WindowTransitionEndListener endListener) {
    return new LollipopWindowTransitionListener(endListener, window);
  }
}

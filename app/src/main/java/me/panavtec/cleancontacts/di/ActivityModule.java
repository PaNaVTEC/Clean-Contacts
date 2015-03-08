package me.panavtec.cleancontacts.di;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.ui.elevation.ElevationHandler;
import me.panavtec.cleancontacts.ui.elevation.ElevationHandlerFactory;
import me.panavtec.cleancontacts.ui.errors.ErrorManager;
import me.panavtec.cleancontacts.ui.errors.SnackbarErrorManagerImp;
import me.panavtec.cleancontacts.ui.transitions.WindowTransitionListener;
import me.panavtec.cleancontacts.ui.transitions.WindowTransitionListenerFactory;

@Module(
    addsTo = AppModule.class,
    library = true)
public class ActivityModule {

  private ActionBarActivity activity;

  public ActivityModule(ActionBarActivity activity) {
    this.activity = activity;
  }

  @Provides ActionBar provideActionBar() {
    return activity.getSupportActionBar();
  }

  @Provides Context provideContext() {
    return activity;
  }

  @Provides ActionBarActivity provideActivity() {
    return activity;
  }

  @Provides ErrorManager provideErrorManager() {
    return new SnackbarErrorManagerImp(activity);
  }

  @Provides Window provideWindow() {
    return activity.getWindow();
  }

  @Provides @Singleton ElevationHandler.Factory provideElevationHandlerFactory(
      ElevationHandlerFactory factoryImp) {
    return factoryImp;
  }

  @Provides @Singleton ElevationHandler provideElevationHandler(ElevationHandler.Factory factory) {
    return factory.createElevationHandler();
  }

  @Provides @Singleton WindowTransitionListener.Factory provideWindowTransitionListenerFactory(
      WindowTransitionListenerFactory factoryImp) {
    return factoryImp;
  }

  @Provides @Singleton WindowTransitionListener provideElevationHandler(WindowTransitionListener.Factory factory) {
    return factory.createWindowTransitionListener();
  }
  
}

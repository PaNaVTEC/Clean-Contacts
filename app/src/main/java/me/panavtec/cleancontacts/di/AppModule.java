package me.panavtec.cleancontacts.di;

import android.app.Application;
import android.os.Build;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.CleanContactsApp;
import me.panavtec.cleancontacts.di.qualifiers.ApiLevel;

@Module(
    includes = {
        DataModule.class, UiModule.class, DomainModule.class, PresentationModule.class
    },
    injects = CleanContactsApp.class,
    library = true
)
public class AppModule {

  private final Application app;

  public AppModule(Application app) {
    this.app = app;
  }

  @Provides @Singleton Application provideApplication() {
    return app;
  }

  @Provides @Singleton @ApiLevel int provideApiLevel() {
    return Build.VERSION.SDK_INT;
  }
}

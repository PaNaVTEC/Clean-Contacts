package me.panavtec.cleancontacts.di;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.CleanContactsApp;

import javax.inject.Singleton;

@Module(
        includes = {
                DataModule.class,
                UiModule.class
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

}

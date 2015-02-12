package me.panavtec.cleancontacts;

import android.app.Application;
import me.panavtec.cleancontacts.di.UiModule;
import me.panavtec.cleancontacts.di.DataModule;
import dagger.Module;
import dagger.Provides;

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

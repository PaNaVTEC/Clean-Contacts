package me.panavtec.cleancontacts.desktop.di;

import dagger.Module;
import me.panavtec.cleancontacts.desktop.CleanContactsApp;

@Module(
        includes = {
                DataModule.class,
                UiModule.class
        },
        injects = CleanContactsApp.class,
        library = true
)
public class AppModule {
}

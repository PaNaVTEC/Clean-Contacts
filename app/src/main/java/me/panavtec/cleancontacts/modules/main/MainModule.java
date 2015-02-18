package me.panavtec.cleancontacts.modules.main;

import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorExecutor;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.main.MainView;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
        addsTo = ActivityModule.class,
        injects = MainActivity.class,
        library = true)
public class MainModule {

    private MainView mainView;

    public MainModule(MainView mainView) {
        this.mainView = mainView;
    }

    @Provides @Singleton MainPresenter provideMainPresenter(Bus bus,
                                                            InteractorExecutor interactorExecutor,
                                                            GetContactsInteractor getContactsInteractor) {
        return new MainPresenter(bus, interactorExecutor, getContactsInteractor, mainView);
    }

}

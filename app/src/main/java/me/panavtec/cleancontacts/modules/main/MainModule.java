package me.panavtec.cleancontacts.modules.main;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.main.MainView;

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
      InteractorInvoker interactorInvoker, GetContactsInteractor getContactsInteractor) {
    return new MainPresenter(bus, interactorInvoker, getContactsInteractor, mainView);
  }
}

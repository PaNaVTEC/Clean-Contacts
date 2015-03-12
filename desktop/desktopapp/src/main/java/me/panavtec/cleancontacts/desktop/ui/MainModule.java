package me.panavtec.cleancontacts.desktop.ui;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.desktop.di.AppModule;
import me.panavtec.cleancontacts.desktop.ui.main.FXMLMainController;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.main.MainView;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;

import javax.inject.Singleton;

@Module(
    addsTo = AppModule.class,
    injects = FXMLMainController.class,
    library = true)
public class MainModule {

  private MainView mainView;

  public MainModule(MainView mainView) {
    this.mainView = mainView;
  }

  @Provides @Singleton MainPresenter provideMainPresenter(Bus bus,
      InteractorInvoker interactorInvoker, GetContactsInteractor getContactsInteractor, ListMapper<Contact, PresentationContact> mapper) {
    return new MainPresenter(bus, interactorInvoker, getContactsInteractor, mainView, mapper);
  }
}

package me.panavtec.cleancontacts.desktop.ui;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.desktop.di.AppModule;
import me.panavtec.cleancontacts.desktop.ui.main.FXMLMainController;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.main.MainView;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;

@Module(
    addsTo = AppModule.class,
    injects = FXMLMainController.class,
    library = true)
public class MainModule {

  private MainView mainView;

  public MainModule(MainView mainView) {
    this.mainView = mainView;
  }

  @Provides @Singleton MainPresenter provideMainPresenter(
      InteractorInvoker interactorInvoker, GetContactsInteractor getContactsInteractor, ListMapper<Contact, PresentationContact> mapper, ThreadSpec threadSpec) {
    return new MainPresenter(interactorInvoker, getContactsInteractor, mainView, mapper, threadSpec);
  }
}

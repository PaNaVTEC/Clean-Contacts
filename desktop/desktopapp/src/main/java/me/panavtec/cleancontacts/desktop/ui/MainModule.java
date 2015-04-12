package me.panavtec.cleancontacts.desktop.ui;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.desktop.di.AppModule;
import me.panavtec.cleancontacts.desktop.ui.main.FXMLMainController;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.presentation.modules.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.modules.main.MainView;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.outputs.interactors.base.ThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactsInteractor;

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

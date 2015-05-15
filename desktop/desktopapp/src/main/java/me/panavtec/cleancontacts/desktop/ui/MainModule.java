package me.panavtec.cleancontacts.desktop.ui;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.desktop.di.AppModule;
import me.panavtec.cleancontacts.desktop.ui.main.FXMLMainController;
import me.panavtec.cleancontacts.domain.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.domain.model.PresentationContact;
import me.panavtec.cleancontacts.domain.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.domain.modules.main.MainPresenter;
import me.panavtec.cleancontacts.domain.modules.main.MainView;
import me.panavtec.cleancontacts.domain.outputs.ThreadSpec;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;

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
      InteractorInvoker interactorInvoker, GetContactsInteractor getContactsInteractor,
      ListMapper<Contact, PresentationContact> mapper, ThreadSpec threadSpec) {
    return new MainPresenter(interactorInvoker, getContactsInteractor, mapper, threadSpec);
  }
}

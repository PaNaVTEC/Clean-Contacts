package me.panavtec.cleancontacts.modules.main;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.presentation.CleanContactsViewInjector;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.presentation.modules.main.MainPresenter;

@Module(
    addsTo = ActivityModule.class,
    injects = MainActivity.class) public class MainModule {

  @Provides @Singleton MainPresenter provideMainPresenter(InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor,
      ListMapper<Contact, PresentationContact> listMapper, CleanContactsViewInjector viewInjector) {
    return new MainPresenter(interactorInvoker, getContactsInteractor, listMapper, viewInjector);
  }
}

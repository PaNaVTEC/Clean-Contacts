package me.panavtec.cleancontacts.modules.main;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.di.qualifiers.MainThread;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;

@Module(
    addsTo = ActivityModule.class,
    injects = MainActivity.class)
public class MainModule {

  @Provides @Singleton MainPresenter provideMainPresenter(InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor,
      ListMapper<Contact, PresentationContact> listMapper, @MainThread ThreadSpec mainThreadSpec) {
    return new MainPresenter(interactorInvoker, getContactsInteractor, listMapper, mainThreadSpec);
  }
}

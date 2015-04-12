package me.panavtec.cleancontacts.modules.main;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.di.qualifiers.MainThread;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.presentation.modules.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.outputs.ThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.presentation.outputs.interactors.contacts.GetContactsInteractor;

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

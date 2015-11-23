package me.panavtec.cleancontacts.modules.main;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.di.qualifiers.UiThread;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.presentation.modules.main.MainPresenter;
import me.panavtec.threaddecoratedview.views.ThreadSpec;

@Module(
    addsTo = ActivityModule.class,
    injects = MainActivity.class)
public class MainModule {

  @Provides @Singleton MainPresenter provideMainPresenter(InteractorInvoker interactorInvoker,
      GetContactsInteractor getContactsInteractor,
      ListMapper<Contact, PresentationContact> listMapper, @UiThread ThreadSpec mainThreadSpec) {
    return new MainPresenter(interactorInvoker, getContactsInteractor, listMapper, mainThreadSpec);
  }
}

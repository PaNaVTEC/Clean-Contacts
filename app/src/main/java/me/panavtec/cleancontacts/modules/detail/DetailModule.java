package me.panavtec.cleancontacts.modules.detail;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.CleanContactsViewInjector;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.presentation.modules.detail.DetailPresenter;

@Module(
    addsTo = ActivityModule.class,
    library = true,
    injects = DetailActivity.class) public class DetailModule {

  private String contactMd5;

  public DetailModule(String contactMd5) {
    this.contactMd5 = contactMd5;
  }

  @Provides DetailPresenter providePresenter(InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor, PresentationContactMapper contactMapper,
      CleanContactsViewInjector viewInjector) {
    return new DetailPresenter(contactMd5, interactorInvoker, getContactInteractor, contactMapper,
        viewInjector);
  }
}

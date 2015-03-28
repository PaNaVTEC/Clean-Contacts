package me.panavtec.cleancontacts.modules.detail;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.detail.DetailPresenter;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.ui.transitions.WindowTransitionListener;

@Module(
    addsTo = ActivityModule.class,
    library = true,
    injects = DetailActivity.class)
public class DetailModule {

  private DetailActivity detailActivity;
  private String contactMd5;

  public DetailModule(DetailActivity detailActivity, String contactMd5) {
    this.detailActivity = detailActivity;
    this.contactMd5 = contactMd5;
  }

  @Provides DetailPresenter providePresenter(InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor, PresentationContactMapper contactMapper, ThreadSpec mainThread) {
    return new DetailPresenter(contactMd5, interactorInvoker, getContactInteractor, detailActivity,
        contactMapper, mainThread);
  }

  @Provides WindowTransitionListener.WindowTransitionEndListener provideEndListener() {
    return detailActivity;
  }
}

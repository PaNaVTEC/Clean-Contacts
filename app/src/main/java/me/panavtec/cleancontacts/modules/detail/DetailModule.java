package me.panavtec.cleancontacts.modules.detail;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
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

  public DetailModule(DetailActivity detailActivity) {
    this.detailActivity = detailActivity;
  }

  @Provides DetailPresenter providePresenter(Bus bus, InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor, PresentationContactMapper contactMapper) {
    return new DetailPresenter(bus, interactorInvoker, getContactInteractor, detailActivity,
        contactMapper);
  }

  @Provides WindowTransitionListener.WindowTransitionEndListener provideEndListener() {
    return detailActivity;
  }
}

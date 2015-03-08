package me.panavtec.cleancontacts.modules.detail;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.detail.DetailPresenter;
import me.panavtec.cleancontacts.ui.Coordinator;

@Module(
    addsTo = ActivityModule.class,
    injects = DetailActivity.class)
public class DetailModule {

  private DetailActivity detailActivity;
  private String[] coordinatorActions;

  public DetailModule(DetailActivity detailActivity, String[] coordinatorActions) {
    this.detailActivity = detailActivity;
    this.coordinatorActions = coordinatorActions;
  }

  @Provides DetailPresenter providePresenter(Bus bus, InteractorInvoker interactorInvoker,
      GetContactInteractor getContactInteractor) {
    return new DetailPresenter(bus, interactorInvoker, getContactInteractor, detailActivity);
  }

  @Provides Coordinator provideCoordinator() {
    return new Coordinator(detailActivity, coordinatorActions);
  }
}

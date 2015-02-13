package me.panavtec.cleancontacts.modules.detail;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.EventBus;
import me.panavtec.cleancontacts.domain.interactors.InteractorExecutor;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.presentation.detail.DetailPresenter;
import me.panavtec.cleancontacts.presentation.detail.DetailView;

@Module(
        addsTo = ActivityModule.class,
        injects = DetailActivity.class,
        library = true)

public class DetailModule {

    private DetailView detailView;

    public DetailModule(DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides DetailPresenter providePresenter(EventBus bus,
                                               InteractorExecutor interactorExecutor,
                                               GetContactInteractor getContactInteractor) {
        return new DetailPresenter(bus, interactorExecutor, getContactInteractor, detailView);
    }
    
}

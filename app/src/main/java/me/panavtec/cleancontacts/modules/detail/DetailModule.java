package me.panavtec.cleancontacts.modules.detail;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
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

    @Provides DetailPresenter providePresenter(Bus bus,
                                               InteractorInvoker interactorInvoker,
                                               GetContactInteractor getContactInteractor) {
        return new DetailPresenter(bus, interactorInvoker, getContactInteractor, detailView);
    }
    
}

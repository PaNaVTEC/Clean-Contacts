package me.panavtec.cleancontacts.modules.detail;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.di.ActivityModule;
import me.panavtec.cleancontacts.presentation.detail.DetailPresenter;

@Module(
        addsTo = ActivityModule.class,
        injects = DetailActivity.class,
        library = true)
public class DetailModule {

    @Provides DetailPresenter providePresenter(){
        return new DetailPresenter();
    }
    
}

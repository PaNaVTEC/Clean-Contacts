package me.panavtec.cleancontacts.modules.detail;

import dagger.Module;
import me.panavtec.cleancontacts.di.ActivityModule;

@Module(
        addsTo = ActivityModule.class,
        injects = DetailActivity.class,
        library = true)
public class DetailModule {

}

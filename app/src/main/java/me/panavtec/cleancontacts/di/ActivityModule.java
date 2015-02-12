package me.panavtec.cleancontacts.di;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import me.panavtec.cleancontacts.AppModule;
import me.panavtec.cleancontacts.ui.errors.ErrorManager;
import me.panavtec.cleancontacts.ui.errors.SnackbarErrorManagerImp;
import dagger.Module;
import dagger.Provides;

@Module(
        addsTo = AppModule.class,
        library = true
)
public class ActivityModule {

    private ActionBarActivity activity;

    public ActivityModule(ActionBarActivity activity) {
        this.activity = activity;
    }

    @Provides ActionBar provideActionBar() {
        return activity.getSupportActionBar();
    }

    @Provides Context provideContext() {
        return activity;
    }

    @Provides ActionBarActivity provideActivity() {
        return activity;
    }

    @Provides ErrorManager provideErrorManager() {
        return new SnackbarErrorManagerImp(activity);
    }

}

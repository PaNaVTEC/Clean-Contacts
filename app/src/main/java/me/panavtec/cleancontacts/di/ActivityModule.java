package me.panavtec.cleancontacts.di;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.ui.elevation.ElevationHandler;
import me.panavtec.cleancontacts.ui.elevation.LollipopElevationHandler;
import me.panavtec.cleancontacts.ui.elevation.NoElevationHandler;
import me.panavtec.cleancontacts.ui.errors.ErrorManager;
import me.panavtec.cleancontacts.ui.errors.SnackbarErrorManagerImp;

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

    @Provides ElevationHandler provideElevationUtil(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new LollipopElevationHandler(context);
        } else {
            return new NoElevationHandler(context);
        }
    }
}

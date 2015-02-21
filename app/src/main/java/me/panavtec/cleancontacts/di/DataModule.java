package me.panavtec.cleancontacts.di;

import android.app.Application;
import android.os.Build;
import com.path.android.jobqueue.JobManager;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.BuildConfig;
import me.panavtec.cleancontacts.data.RetrofitLog;
import me.panavtec.cleancontacts.data.UserAgent;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.BusImp;
import me.panavtec.cleancontacts.domain.InteractorInvokerImp;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoaderImp;
import retrofit.Endpoint;
import retrofit.Endpoints;

import javax.inject.Singleton;

@Module(
        includes = {
                InteractorsModule.class,
                RepositoryModule.class,
        },
        complete = false,
        library = true
)
public class DataModule {

    @Provides @Singleton Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(BuildConfig.API_URL);
    }

    @Provides @Singleton @RetrofitLog boolean provideRetrofitLog() {
        return BuildConfig.RETROFIT_LOG;
    }

    @Provides @Singleton @UserAgent String provideUserAgent() {
        return String.format("Sample-Android;%s;%s;%s;%d;", Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE, BuildConfig.VERSION_CODE);
    }

    @Provides @Singleton Bus provideEventbus() {
        return new BusImp();
    }

    @Provides @Singleton JobManager provideJobManager(Application app) {
        return new JobManager(app);
    }

    @Provides @Singleton InteractorInvoker provideInteractorInvoker(JobManager jobManager, Bus bus) {
        return new InteractorInvokerImp(jobManager);
    }

    @Provides @Singleton Picasso providePicasso(Application app) {
        return Picasso.with(app);
    }

    @Provides @Singleton ImageLoader provideImageLoader(Picasso picasso) {
        return new ImageLoaderImp(picasso);
    }

}

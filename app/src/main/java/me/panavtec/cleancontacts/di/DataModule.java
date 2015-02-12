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
import me.panavtec.cleancontacts.domain.EventBus;
import me.panavtec.cleancontacts.domain.EventBusImp;
import me.panavtec.cleancontacts.domain.InteractorExecutorImp;
import me.panavtec.cleancontacts.domain.interactors.InteractorExecutor;
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

    @Provides @Singleton EventBus provideEventbus() {
        return new EventBusImp();
    }

    @Provides @Singleton JobManager provideJobManager(Application app) {
        return new JobManager(app);
    }

    @Provides @Singleton InteractorExecutor provideInteractorExecutor(JobManager jobManager, EventBus bus) {
        return new InteractorExecutorImp(jobManager, bus);
    }

    @Provides @Singleton Picasso providePicasso(Application app) {
        return Picasso.with(app);
    }

    @Provides @Singleton ImageLoader provideImageLoader(Picasso picasso) {
        return new ImageLoaderImp(picasso);
    }

}

package me.panavtec.cleancontacts.di;

import android.os.Build;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.BuildConfig;
import me.panavtec.cleancontacts.data.RetrofitLog;
import me.panavtec.cleancontacts.data.UserAgent;
import retrofit.Endpoint;
import retrofit.Endpoints;

@Module(
    includes = {
        InteractorsModule.class, RepositoryModule.class,
    },
    complete = false,
    library = true)
public class DataModule {

  @Provides @Singleton Endpoint provideEndpoint() {
    return Endpoints.newFixedEndpoint(BuildConfig.API_URL);
  }

  @Provides @Singleton @RetrofitLog boolean provideRetrofitLog() {
    return BuildConfig.RETROFIT_LOG;
  }

  @Provides @Singleton @UserAgent String provideUserAgent() {
    return String.format("Sample-Android;%s;%s;%s;%d;", Build.MANUFACTURER, Build.MODEL,
        Build.VERSION.RELEASE, BuildConfig.VERSION_CODE);
  }
}

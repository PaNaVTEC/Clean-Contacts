package me.panavtec.cleancontacts.desktop.di;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.desktop.data.RetrofitLog;
import me.panavtec.cleancontacts.desktop.data.UserAgent;
import me.panavtec.cleancontacts.desktop.domain.BusImp;
import me.panavtec.cleancontacts.desktop.domain.InteractorInvokerImp;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.InteractorInvoker;
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
    return Endpoints.newFixedEndpoint("http://api.randomuser.me/");
  }

  @Provides @Singleton @RetrofitLog boolean provideRetrofitLog() {
    return true;
  }

  @Provides @Singleton @UserAgent String provideUserAgent() {
    return String.format("Sample-JavaFX-1.0");
  }

  @Provides @Singleton Bus provideEventbus() {
    return new BusImp();
  }

  @Provides @Singleton InteractorInvoker provideInteractorInvoker(ExecutorService executorService) {
    return new InteractorInvokerImp(executorService);
  }

  @Provides @Singleton ExecutorService provideExecutorService() {
    return Executors.newFixedThreadPool(5);
  }
}

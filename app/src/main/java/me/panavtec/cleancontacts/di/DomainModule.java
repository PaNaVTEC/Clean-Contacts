package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvokerImp;
import me.panavtec.cleancontacts.presentation.invoker.InteractorOutputThreadFactory;

@Module(
    includes = {
        InteractorsModule.class, RepositoryModule.class
    },
    complete = false,
    library = true)
public class DomainModule {

  @Provides @Singleton InteractorInvoker provideInteractorInvoker(ExecutorService executor) {
    return new InteractorInvokerImp(executor);
  }

  @Provides @Singleton ExecutorService provideExecutor(ThreadFactory threadFactory) {
    return Executors.newFixedThreadPool(3, threadFactory);
  }

  @Provides @Singleton ThreadFactory provideThreadFactory() {
    return new InteractorOutputThreadFactory();
  }
}

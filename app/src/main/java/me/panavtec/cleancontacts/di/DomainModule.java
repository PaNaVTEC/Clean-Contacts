package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.BuildConfig;
import me.panavtec.cleancontacts.domain.invoker.InteractorInvokerImp;
import me.panavtec.cleancontacts.domain.invoker.InteractorOutputThreadFactory;
import me.panavtec.cleancontacts.domain.invoker.InteractorPriorityBlockingQueue;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;

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

  @Provides @Singleton ExecutorService provideExecutor(ThreadFactory threadFactory,
      BlockingQueue<Runnable> blockingQueue) {
    return new ThreadPoolExecutor(BuildConfig.CONCURRENT_INTERACTORS,
        BuildConfig.CONCURRENT_INTERACTORS, 0L, TimeUnit.MILLISECONDS, blockingQueue,
        threadFactory);
  }

  @Provides @Singleton public BlockingQueue<Runnable> provideBlockingQueue() {
    return new InteractorPriorityBlockingQueue(100);
  }

  @Provides @Singleton ThreadFactory provideThreadFactory() {
    return new InteractorOutputThreadFactory();
  }
}

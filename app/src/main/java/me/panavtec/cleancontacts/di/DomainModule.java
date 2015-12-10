package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.BuildConfig;
import me.panavtec.cleancontacts.domain.invoker.InteractorInvokerImp;
import me.panavtec.cleancontacts.domain.invoker.InteractorOutputThreadFactory;
import me.panavtec.cleancontacts.domain.invoker.InteractorPriorityBlockingQueue;
import me.panavtec.cleancontacts.domain.invoker.LogExceptionHandler;
import me.panavtec.cleancontacts.domain.invoker.PriorizableThreadPoolExecutor;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;

@Module(
    includes = {
        InteractorsModule.class
    },
    complete = false,
    library = true) public class DomainModule {

  @Provides @Singleton InteractorInvoker provideInteractorInvoker(ExecutorService executor,
      LogExceptionHandler logExceptionHandler) {
    return new InteractorInvokerImp(executor, logExceptionHandler);
  }

  @Provides @Singleton LogExceptionHandler provideLogExceptionHandler() {
    return new LogExceptionHandler();
  }

  @Provides @Singleton ExecutorService provideExecutor(ThreadFactory threadFactory,
      BlockingQueue<Runnable> blockingQueue) {
    return new PriorizableThreadPoolExecutor(BuildConfig.CONCURRENT_INTERACTORS,
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

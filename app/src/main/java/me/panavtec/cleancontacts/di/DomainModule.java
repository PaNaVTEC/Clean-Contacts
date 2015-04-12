package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.di.qualifiers.BackThread;
import me.panavtec.cleancontacts.di.qualifiers.SameThread;
import me.panavtec.cleancontacts.di.qualifiers.UiThread;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvokerImp;
import me.panavtec.cleancontacts.presentation.outputs.BackThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.MainThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.SameThreadSpec;
import me.panavtec.presentation.common.ThreadSpec;

@Module(
    includes = {
        InteractorsModule.class, RepositoryModule.class
    },
    complete = false,
    library = true)
public class DomainModule {

  @Provides @Singleton @UiThread ThreadSpec provideMainThread() {
    return new MainThreadSpec();
  }

  @Provides @Singleton @SameThread ThreadSpec provideSameThread() {
    return new SameThreadSpec();
  }

  @Provides @Singleton @BackThread ThreadSpec provideBackThread() {
    return new BackThreadSpec();
  }

  @Provides @Singleton InteractorInvoker provideInteractorInvoker(ExecutorService executor) {
    return new InteractorInvokerImp(executor);
  }

  @Provides @Singleton ExecutorService provideExecutor() {
    return Executors.newFixedThreadPool(3);
  }
}

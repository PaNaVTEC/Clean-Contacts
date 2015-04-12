package me.panavtec.cleancontacts.di;

import android.app.Application;
import com.path.android.jobqueue.JobManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.di.qualifiers.BackThread;
import me.panavtec.cleancontacts.di.qualifiers.MainThread;
import me.panavtec.cleancontacts.di.qualifiers.SameThread;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvoker;
import me.panavtec.cleancontacts.presentation.invoker.InteractorInvokerImp;
import me.panavtec.cleancontacts.presentation.outputs.BackThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.MainThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.SameThreadSpec;
import me.panavtec.cleancontacts.presentation.outputs.ThreadSpec;

@Module(
    includes = {
        InteractorsModule.class, RepositoryModule.class
    },
    complete = false,
    library = true
)
public class DomainModule {

  @Provides @Singleton @MainThread ThreadSpec provideMainThread() {
    return new MainThreadSpec();
  }

  @Provides @Singleton @SameThread ThreadSpec provideSameThread() {
    return new SameThreadSpec();
  }

  @Provides @Singleton @BackThread ThreadSpec provideBackThread() {
    return new BackThreadSpec();
  }

  @Provides @Singleton JobManager provideJobManager(Application app) {
    return new JobManager(app);
  }

  @Provides @Singleton InteractorInvoker provideInteractorInvoker(JobManager jobManager) {
    return new InteractorInvokerImp(jobManager);
  }
  
}

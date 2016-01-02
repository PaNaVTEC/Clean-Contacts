package me.panavtec.cleancontacts;

import android.os.AsyncTask;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;

@Module(
    complete = false,
    library = true,
    overrides = true) public class TestModule {

  @Provides @Singleton ExecutorService provideExecutor() {
    return (ExecutorService) AsyncTask.THREAD_POOL_EXECUTOR;
  }

}

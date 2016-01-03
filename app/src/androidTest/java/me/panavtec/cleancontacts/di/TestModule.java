package me.panavtec.cleancontacts.di;

import android.os.AsyncTask;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;

@Module(
    complete = false,
    library = true,
    overrides = true) public class TestModule {

  @Provides @Singleton ExecutorService provideAsyncTaskExecutor() {
    return (ExecutorService) AsyncTask.THREAD_POOL_EXECUTOR;
  }

}

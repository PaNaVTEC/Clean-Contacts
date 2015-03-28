package me.panavtec.cleancontacts.di;

import android.app.Application;
import android.os.Build;
import com.path.android.jobqueue.JobManager;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.BuildConfig;
import me.panavtec.cleancontacts.data.RetrofitLog;
import me.panavtec.cleancontacts.data.UserAgent;
import me.panavtec.cleancontacts.domain.InteractorInvokerImp;
import me.panavtec.cleancontacts.domain.MainThreadSpec;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.interactors.base.InteractorInvoker;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;
import me.panavtec.cleancontacts.ui.imageloader.PicassoImageLoader;
import retrofit.Endpoint;
import retrofit.Endpoints;

@Module(
    includes = {
        InteractorsModule.class, RepositoryModule.class,
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
    return String.format("Sample-Android;%s;%s;%s;%d;", Build.MANUFACTURER, Build.MODEL,
        Build.VERSION.RELEASE, BuildConfig.VERSION_CODE);
  }

  @Provides @Singleton JobManager provideJobManager(Application app) {
    return new JobManager(app);
  }

  @Provides @Singleton InteractorInvoker provideInteractorInvoker(JobManager jobManager) {
    return new InteractorInvokerImp(jobManager);
  }

  @Provides @Singleton Picasso providePicasso(Application app) {
    return Picasso.with(app);
  }

  @Provides @Singleton ImageLoader provideImageLoader(Picasso picasso) {
    return new PicassoImageLoader(picasso);
  }

  @Provides @Singleton PresentationContactMapper providePresentationContactMapper() {
    return new PresentationContactMapper();
  }

  @Provides @Singleton ListMapper<Contact, PresentationContact> providePresentationContactMapper(
      PresentationContactMapper presentationContactMapper) {
    return new ListMapper<>(presentationContactMapper);
  }

  @Provides @Singleton ThreadSpec provideMainThread() {
    return new MainThreadSpec();
  }

  //@Provides @Singleton BackThreadSpec provideBackThread() {
  //  return new BackThreadSpec();
  //}
  
}

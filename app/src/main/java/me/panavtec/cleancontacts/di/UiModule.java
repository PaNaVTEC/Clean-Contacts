package me.panavtec.cleancontacts.di;

import android.app.Application;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.di.qualifiers.UiThread;
import me.panavtec.cleancontacts.presentation.CleanContactsViewInjector;
import me.panavtec.cleancontacts.presentation.ViewInjectorImp;
import me.panavtec.cleancontacts.ui.imageloader.ImageLoader;
import me.panavtec.cleancontacts.ui.imageloader.PicassoImageLoader;
import me.panavtec.threaddecoratedview.views.ThreadSpec;

@Module(
    complete = false,
    library = true) public class UiModule {

  @Provides @Singleton Picasso providePicasso(Application app) {
    return Picasso.with(app);
  }

  @Provides @Singleton ImageLoader provideImageLoader(Picasso picasso) {
    return new PicassoImageLoader(picasso);
  }

  @Provides @Singleton CleanContactsViewInjector provideViewInjector(ViewInjectorImp imp) {
    return imp;
  }

  @Provides @Singleton ViewInjectorImp provieViewInjectorImp(@UiThread ThreadSpec threadSpec) {
    return new ViewInjectorImp(threadSpec);
  }
}

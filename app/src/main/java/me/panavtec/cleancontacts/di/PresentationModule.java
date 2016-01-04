package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.di.qualifiers.BackThread;
import me.panavtec.cleancontacts.di.qualifiers.SameThread;
import me.panavtec.cleancontacts.di.qualifiers.UiThread;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.outputs.BackThreadSpec;
import me.panavtec.cleancontacts.domain.outputs.MainThreadSpec;
import me.panavtec.cleancontacts.domain.outputs.SameThreadSpec;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.domain.mappers.ListMapper;
import me.panavtec.threaddecoratedview.views.ThreadSpec;

@Module(
    complete = false,
    library = true
)
public class PresentationModule {

  @Provides @Singleton @UiThread
  ThreadSpec provideMainThread() {
    return new MainThreadSpec();
  }

  @Provides @Singleton @SameThread ThreadSpec provideSameThread() {
    return new SameThreadSpec();
  }

  @Provides @Singleton @BackThread ThreadSpec provideBackThread() {
    return new BackThreadSpec();
  }
  
  @Provides @Singleton PresentationContactMapper providePresentationContactMapper() {
    return new PresentationContactMapper();
  }

  @Provides @Singleton ListMapper<Contact, PresentationContact> providePresentationContactMapper(
      PresentationContactMapper presentationContactMapper) {
    return new ListMapper<>(presentationContactMapper);
  }
  
}

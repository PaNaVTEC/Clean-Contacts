package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.PresentationContactMapper;
import me.panavtec.cleancontacts.presentation.model.mapper.base.ListMapper;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;

@Module(
    complete = false,
    library = true
)
public class PresentationModule {

  @Provides @Singleton PresentationContactMapper providePresentationContactMapper() {
    return new PresentationContactMapper();
  }

  @Provides @Singleton ListMapper<Contact, PresentationContact> providePresentationContactMapper(
      PresentationContactMapper presentationContactMapper) {
    return new ListMapper<>(presentationContactMapper);
  }
  
}

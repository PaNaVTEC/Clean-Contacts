package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;

@Module(
    complete = false,
    library = true
)
public class InteractorsModule {

  @Provides @Singleton GetContactsInteractor provideGetContactsInteractor(
      ContactsRepository repository) {
    return new GetContactsInteractor(repository);
  }

  @Provides @Singleton GetContactInteractor provideGetContactInteractor(
      ContactsRepository repository) {
    return new GetContactInteractor(repository);
  }
}

package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.domain.abstractions.Bus;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;

@Module(
    complete = false,
    library = true
)
public class InteractorsModule {

  @Provides @Singleton GetContactsInteractor provideGetContactsInteractor(Bus bus,
      ContactsRepository repository) {
    return new GetContactsInteractor(bus, repository);
  }

  @Provides @Singleton GetContactInteractor provideGetContactInteractor(Bus bus,
      ContactsRepository repository) {
    return new GetContactInteractor(bus, repository);
  }
}

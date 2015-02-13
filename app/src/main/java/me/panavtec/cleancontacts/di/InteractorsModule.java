package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.domain.EventBus;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;

import javax.inject.Singleton;

@Module(
        complete = false,
        library = true
)
public class InteractorsModule {

    @Provides @Singleton GetContactsInteractor provideGetContactsInteractor(EventBus bus, ContactsRepository repository) {
        return new GetContactsInteractor(bus, repository);
    }

    @Provides @Singleton GetContactInteractor provideGetContactInteractor(EventBus bus, ContactsRepository repository) {
        return new GetContactInteractor(bus, repository);
    }

}

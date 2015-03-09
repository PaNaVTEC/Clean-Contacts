package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.repository.CachingStrategy;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;
import me.panavtec.cleancontacts.repository.contacts.ContactCachingStrategy;
import me.panavtec.cleancontacts.repository.contacts.ContactsRepositoryImp;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;

@Module(
    includes = {
        ApiModule.class, BddModule.class
    },
    complete = false,
    library = true
)
public class RepositoryModule {

  @Provides @Singleton ContactsRepository provideContactsRepository(
      ContactsNetworkDataSource networkDataSource, ContactsBddDataSource bddDataSource, CachingStrategy<Contact> cachingStrategy) {
    return new ContactsRepositoryImp(networkDataSource, bddDataSource, cachingStrategy);
  }

  @Provides @Singleton CachingStrategy<Contact> provideCachingStrategy() {
    return new ContactCachingStrategy();
  }
    
}

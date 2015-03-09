package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;
import me.panavtec.cleancontacts.repository.caching.strategy.CachingStrategy;
import me.panavtec.cleancontacts.repository.caching.strategy.ListCachingStrategy;
import me.panavtec.cleancontacts.repository.contacts.ContactsRepositoryImp;
import me.panavtec.cleancontacts.repository.contacts.caching.ContactsTtlCachingStrategy;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;

@Module(
    includes = {
        ApiModule.class, BddModule.class
    },
    complete = false,
    library = true)
public class RepositoryModule {

  @Provides @Singleton ContactsRepository provideContactsRepository(
      ContactsNetworkDataSource networkDataSource, ContactsBddDataSource bddDataSource,
      CachingStrategy<Contact> cachingStrategy,
      ListCachingStrategy<Contact> contactListStrategy) {
    return new ContactsRepositoryImp(networkDataSource, bddDataSource, cachingStrategy,
        contactListStrategy);
  }

  @Provides @Singleton CachingStrategy<Contact> provideContactCachingStrategy() {
    return new ContactsTtlCachingStrategy(1, TimeUnit.MINUTES);
  }

  @Provides @Singleton ListCachingStrategy<Contact> provideListContactCachingStrategy(
      CachingStrategy<Contact> strategy) {
    return new ListCachingStrategy<>(strategy);
  }
}

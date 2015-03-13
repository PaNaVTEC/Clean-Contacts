package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
<<<<<<< HEAD
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsApiService;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsNetworkDataSourceImp;
=======
import javax.inject.Singleton;
>>>>>>> release/0.8
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;
import me.panavtec.cleancontacts.repository.contacts.ContactsRepositoryImp;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;

@Module(
<<<<<<< HEAD
        includes = {
                ApiModule.class,
                BddModule.class
        },
        complete = false,
        library = true
)
public class RepositoryModule {

    @Provides @Singleton ContactsRepository provideGameRepository(ContactsNetworkDataSource networkDataSource,
                                                                  ContactsBddDataSource bddDataSource) {
        return new ContactsRepositoryImp(networkDataSource, bddDataSource);
    }

    @Provides @Singleton ContactsNetworkDataSource provideContactsNetworkDataSource(ContactsApiService apiService) {
        return new ContactsNetworkDataSourceImp(apiService);
    }
=======
    includes = {
        ApiModule.class, BddModule.class
    },
    complete = false,
    library = true)
public class RepositoryModule {

  @Provides @Singleton ContactsRepository provideContactsRepository(
      ContactsNetworkDataSource networkDataSource, ContactsBddDataSource bddDataSource) {
    return new ContactsRepositoryImp(networkDataSource, bddDataSource);
  }
>>>>>>> release/0.8

}

package me.panavtec.cleancontacts.di;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import me.panavtec.cleancontacts.BuildConfig;
import me.panavtec.cleancontacts.data.DatabaseHelper;
import me.panavtec.cleancontacts.data.DatabaseName;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsApiService;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsNetworkDataSourceImp;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors.ContactPersistor;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.ContactsBddDataSourceImp;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors.Persistor;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.domain.repository.ContactsRepository;
import me.panavtec.cleancontacts.repository.contacts.ContactsRepositoryImp;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;

import javax.inject.Singleton;

@Module(
        includes = {
                ApiModule.class,
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

    @Provides @Singleton ContactsBddDataSource provideContactsBddDataSource(Persistor<BddContact> persistor, DatabaseHelper helper) {
        return new ContactsBddDataSourceImp(persistor, helper.getContactDao());
    }
    
    @Provides @Singleton Persistor<BddContact> provideContactPersistor(DatabaseHelper helper) {
        return new ContactPersistor(helper);
    }

    @Provides @Singleton public DatabaseHelper provideDatabaseHelper(@DatabaseName String databaseName, 
                                                                     Application app) {
        return new DatabaseHelper(databaseName, app);
    }

    @Provides @Singleton @DatabaseName String provideDatabaseName() {
        return "cleancontacts"  + (BuildConfig.DEBUG ? "-dev":"") + ".db";
    }

}

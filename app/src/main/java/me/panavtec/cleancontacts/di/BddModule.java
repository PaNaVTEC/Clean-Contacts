package me.panavtec.cleancontacts.di;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.BuildConfig;
import me.panavtec.cleancontacts.data.DatabaseHelper;
import me.panavtec.cleancontacts.data.DatabaseName;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.ContactsBddDataSourceImp;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors.ContactPersistor;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors.Persistor;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsBddDataSource;

@Module(
    complete = false,
    library = true
)
public class BddModule {

  @Provides @Singleton ContactsBddDataSource provideContactsBddDataSource(
      Persistor<BddContact> persistor, DatabaseHelper helper) {
    return new ContactsBddDataSourceImp(persistor, helper.getContactDao());
  }

  @Provides @Singleton Persistor<BddContact> provideContactPersistor(DatabaseHelper helper) {
    return new ContactPersistor(helper);
  }

  @Provides @Singleton public DatabaseHelper provideDatabaseHelper(
      @DatabaseName String databaseName, Application app) {
    return new DatabaseHelper(databaseName, app);
  }

  @Provides @Singleton @DatabaseName String provideDatabaseName() {
    return "cleancontacts" + (BuildConfig.DEBUG ? "-dev" : "") + ".db";
  }
}

package me.panavtec.cleancontacts.di;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.BuildConfig;
import me.panavtec.cleancontacts.data.DatabaseHelper;
import me.panavtec.cleancontacts.data.DatabaseName;
import me.panavtec.cleancontacts.data.repository.caching.strategy.CachingStrategy;
import me.panavtec.cleancontacts.data.repository.caching.strategy.list.ListCachingStrategy;
import me.panavtec.cleancontacts.data.repository.caching.strategy.nullsafe.NotNullCachingStrategy;
import me.panavtec.cleancontacts.data.repository.caching.strategy.ttl.TtlCachingStrategy;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.ContactsLocalGatewayImp;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.mapper.BddContactMapper;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors.ContactPersistor;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors.Persistor;
import me.panavtec.cleancontacts.domain.mappers.TwoWaysMapper;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;

@Module(
    complete = false,
    library = true) public class BddModule {

  @Provides @Singleton CachingStrategy<BddContact> provideContactCachingStrategy() {
    return new NotNullCachingStrategy<>();
  }

  @Provides @Singleton ListCachingStrategy<BddContact> provideListContactCachingStrategy() {
    return new ListCachingStrategy<>(new TtlCachingStrategy<BddContact>(3, TimeUnit.MINUTES));
  }

  @Provides @Singleton ContactsLocalGateway provideContactsLocalGateway(
      Persistor<BddContact> persistor, DatabaseHelper helper,
      CachingStrategy<BddContact> singleContactCachingStrategy,
      ListCachingStrategy<BddContact> listCachingStrategy, TwoWaysMapper<BddContact, Contact> mapper) {
    return new ContactsLocalGatewayImp(persistor, helper.getContactDao(),
        singleContactCachingStrategy, listCachingStrategy, mapper);
  }

  @Provides @Singleton TwoWaysMapper<BddContact, Contact> provideBddContactMapper() {
    return new BddContactMapper();
  }

  @Provides @Singleton Persistor<BddContact> provideContactPersistor(DatabaseHelper helper) {
    return new ContactPersistor(helper);
  }

  @Provides @Singleton
  public DatabaseHelper provideDatabaseHelper(@DatabaseName String databaseName, Application app) {
    return new DatabaseHelper(databaseName, app);
  }

  @Provides @Singleton @DatabaseName String provideDatabaseName() {
    return "cleancontacts" + (BuildConfig.DEBUG ? "-dev" : "") + ".db";
  }
}

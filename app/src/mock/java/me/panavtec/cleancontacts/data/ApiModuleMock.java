package me.panavtec.cleancontacts.data;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.ContactsApiService;

@Module(
    complete = false,
    library = true,
    overrides = true) public class ApiModuleMock {

  @Provides @Singleton ContactsApiService provideApiService(Application context) {
    return new MockContactsApiService(context);
  }

}

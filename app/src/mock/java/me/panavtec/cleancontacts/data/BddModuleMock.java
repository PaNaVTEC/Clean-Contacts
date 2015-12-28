package me.panavtec.cleancontacts.data;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;

@Module(complete = false,
    library = true,
    overrides = true) public class BddModuleMock {

  @Provides @Singleton ContactsLocalGateway provideContactDao() {
    return new InMemoryContactGateway();
  }
}

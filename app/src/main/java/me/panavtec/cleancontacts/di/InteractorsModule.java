package me.panavtec.cleancontacts.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactInteractor;
import me.panavtec.cleancontacts.domain.interactors.contacts.GetContactsInteractor;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;
import me.panavtec.cleancontacts.domain.model.ContactsNetworkGateway;

@Module(
    complete = false,
    library = true) public class InteractorsModule {

  @Provides @Singleton GetContactsInteractor provideGetContactsInteractor(
      ContactsLocalGateway localGateway, ContactsNetworkGateway networkGateway) {
    return new GetContactsInteractor(localGateway, networkGateway);
  }

  @Provides @Singleton GetContactInteractor provideGetContactInteractor(
      ContactsLocalGateway localGateway) {
    return new GetContactInteractor(localGateway);
  }
}

package me.panavtec.cleancontacts.data;

import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Singleton;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsLocalGateway;
import me.panavtec.cleancontacts.domain.model.Location;
import me.panavtec.cleancontacts.domain.model.Picture;

@Module(complete = false,
    library = true,
    overrides = true) public class BddModuleMock {

  @Provides @Singleton ContactsLocalGateway provideContactDao() {
    return new ContactsLocalGateway() {
      @Override public void persist(List<Contact> contacts) {
      }

      @Override public Contact obtain(String md5) {
        Contact contact = new Contact();
        Location location = new Location();
        contact.setLocation(location);
        Picture picture = new Picture();
        picture.setThumbnail("http://static.comicvine.com/uploads/original/11112/111122896/4626657-3259_goku.jpg");
        contact.setPicture(picture);
        return contact;
      }

      @Override public void delete(List<Contact> deleted) {
      }

      @Override public List<Contact> obtainContacts() {
        return null;
      }
    };
  }
}

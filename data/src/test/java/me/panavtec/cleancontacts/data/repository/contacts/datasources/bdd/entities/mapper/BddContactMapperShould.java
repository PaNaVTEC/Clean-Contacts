package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddLocation;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddName;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddPicture;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.Location;
import me.panavtec.cleancontacts.domain.model.Name;
import me.panavtec.cleancontacts.domain.model.Picture;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BddContactMapperShould {

  private final BddContactMapper contactMapper = new BddContactMapper();

  @Test public void create_an_exact_copy_of_a_domain_contact() {
    Contact contact = createFullContact();

    BddContact mappedContact = contactMapper.inverseMap(contact);

    assertThat(contact.getMd5(), is(mappedContact.getMd5()));
    assertThat(contact.getCell(), is(mappedContact.getCell()));
    assertThat(contact.getEmail(), is(mappedContact.getEmail()));
    assertThat(contact.getGender(), is(mappedContact.getGender()));
    assertThat(contact.getName().getFirst(), is(mappedContact.getName().getFirst()));
    assertThat(contact.getName().getLast(), is(mappedContact.getName().getLast()));
    assertThat(contact.getName().getTitle(), is(mappedContact.getName().getTitle()));
    assertThat(contact.getPhone(), is(mappedContact.getPhone()));
    assertThat(contact.getPassword(), is(mappedContact.getPassword()));
    assertThat(contact.getUsername(), is(mappedContact.getUsername()));
    assertThat(contact.getVersion(), is(mappedContact.getVersion()));
    assertThat(contact.getLocation().getCity(), is(mappedContact.getLocation().getCity()));
    assertThat(contact.getLocation().getState(), is(mappedContact.getLocation().getState()));
    assertThat(contact.getLocation().getStreet(), is(mappedContact.getLocation().getStreet()));
    assertThat(contact.getLocation().getZip(), is(mappedContact.getLocation().getZip()));
    assertThat(contact.getPicture().getThumbnail(), is(mappedContact.getPicture().getThumbnail()));
    assertThat(contact.getPicture().getLarge(), is(mappedContact.getPicture().getLarge()));
    assertThat(contact.getPicture().getMedium(), is(mappedContact.getPicture().getMedium()));
  }

  @Test public void create_an_exact_copy_of_a_bdd_contact() {
    BddContact bddContact = createFullBddContact();

    Contact mappedContact = contactMapper.map(bddContact);

    assertThat(bddContact.getMd5(), is(mappedContact.getMd5()));
    assertThat(bddContact.getCell(), is(mappedContact.getCell()));
    assertThat(bddContact.getEmail(), is(mappedContact.getEmail()));
    assertThat(bddContact.getGender(), is(mappedContact.getGender()));
    assertThat(bddContact.getName().getFirst(), is(mappedContact.getName().getFirst()));
    assertThat(bddContact.getName().getLast(), is(mappedContact.getName().getLast()));
    assertThat(bddContact.getName().getTitle(), is(mappedContact.getName().getTitle()));
    assertThat(bddContact.getPhone(), is(mappedContact.getPhone()));
    assertThat(bddContact.getPassword(), is(mappedContact.getPassword()));
    assertThat(bddContact.getUsername(), is(mappedContact.getUsername()));
    assertThat(bddContact.getVersion(), is(mappedContact.getVersion()));
    assertThat(bddContact.getLocation().getCity(), is(mappedContact.getLocation().getCity()));
    assertThat(bddContact.getLocation().getState(), is(mappedContact.getLocation().getState()));
    assertThat(bddContact.getLocation().getStreet(), is(mappedContact.getLocation().getStreet()));
    assertThat(bddContact.getLocation().getZip(), is(mappedContact.getLocation().getZip()));
    assertThat(bddContact.getPicture().getThumbnail(), is(mappedContact.getPicture().getThumbnail()));
    assertThat(bddContact.getPicture().getLarge(), is(mappedContact.getPicture().getLarge()));
    assertThat(bddContact.getPicture().getMedium(), is(mappedContact.getPicture().getMedium()));
  }

  private BddContact createFullBddContact() {
    BddContact contact = new BddContact();
    contact.setMd5("md5");
    contact.setCell("cell");
    contact.setEmail("email");
    contact.setGender("gender");
    contact.setPhone("phone");
    contact.setPassword("password");
    contact.setUsername("username");
    contact.setVersion("version");

    BddName name = new BddName();
    name.setTitle("title");
    name.setFirst("first");
    name.setLast("last");
    contact.setName(name);

    BddLocation location = new BddLocation();
    location.setCity("city");
    location.setState("state");
    location.setStreet("street");
    location.setZip("zip");
    contact.setLocation(location);

    BddPicture picture = new BddPicture();
    picture.setThumbnail("thumbnail");
    picture.setLarge("large");
    picture.setMedium("medium");
    contact.setPicture(picture);

    return contact;
  }

  private Contact createFullContact() {
    Contact contact = new Contact();
    contact.setMd5("md5");
    contact.setCell("cell");
    contact.setEmail("email");
    contact.setGender("gender");
    contact.setPhone("phone");
    contact.setPassword("password");
    contact.setUsername("username");
    contact.setVersion("version");

    Name name = new Name();
    name.setTitle("title");
    name.setFirst("first");
    name.setLast("last");
    contact.setName(name);

    Location location = new Location();
    location.setCity("city");
    location.setState("state");
    location.setStreet("street");
    location.setZip("zip");
    contact.setLocation(location);

    Picture picture = new Picture();
    picture.setThumbnail("thumbnail");
    picture.setLarge("large");
    picture.setMedium("medium");
    contact.setPicture(picture);

    return contact;
  }

}
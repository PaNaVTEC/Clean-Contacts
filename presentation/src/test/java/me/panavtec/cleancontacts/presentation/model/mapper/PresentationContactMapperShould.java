package me.panavtec.cleancontacts.presentation.model.mapper;

import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.Location;
import me.panavtec.cleancontacts.domain.model.Name;
import me.panavtec.cleancontacts.domain.model.Picture;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PresentationContactMapperShould {

  private final PresentationContactMapper contactMapper = new PresentationContactMapper();

  @Test public void return_null_if_called_with_null() {
    assertThat(contactMapper.map(null), nullValue(PresentationContact.class));
  }

  @Test public void create_an_exact_copy_of_a_domain_contact() {
    Contact contact = createFullContact();

    PresentationContact mappedContact = contactMapper.map(contact);

    assertThat(contact.getMd5(), is(mappedContact.getMd5()));
    assertThat(contact.getCell(), is(mappedContact.getCell()));
    assertThat(contact.getEmail(), is(mappedContact.getEmail()));
    assertThat(contact.getGender(), is(mappedContact.getGender()));
    assertThat(contact.getName().getFirst(), is(mappedContact.getFirstName()));
    assertThat(contact.getName().getLast(), is(mappedContact.getLastName()));
    assertThat(contact.getName().getTitle(), is(mappedContact.getTitle()));
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
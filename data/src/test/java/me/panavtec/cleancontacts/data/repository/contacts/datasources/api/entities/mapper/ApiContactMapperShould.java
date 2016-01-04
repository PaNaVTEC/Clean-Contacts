package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiLocation;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiName;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiPicture;
import me.panavtec.cleancontacts.domain.model.Contact;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiContactMapperShould {

  private final ApiContactMapper contactMapper = new ApiContactMapper();

  @Test public void create_an_exact_copy_of_a_api_contact() {
    ApiContact apiContact = createFullContact();

    Contact mappedContact = contactMapper.map(apiContact);

    assertThat(apiContact.getMd5(), is(mappedContact.getMd5()));
    assertThat(apiContact.getCell(), is(mappedContact.getCell()));
    assertThat(apiContact.getEmail(), is(mappedContact.getEmail()));
    assertThat(apiContact.getGender(), is(mappedContact.getGender()));
    assertThat(apiContact.getName().getFirst(), is(mappedContact.getName().getFirst()));
    assertThat(apiContact.getName().getLast(), is(mappedContact.getName().getLast()));
    assertThat(apiContact.getName().getTitle(), is(mappedContact.getName().getTitle()));
    assertThat(apiContact.getPhone(), is(mappedContact.getPhone()));
    assertThat(apiContact.getPassword(), is(mappedContact.getPassword()));
    assertThat(apiContact.getUsername(), is(mappedContact.getUsername()));
    assertThat(apiContact.getVersion(), is(mappedContact.getVersion()));
    assertThat(apiContact.getLocation().getCity(), is(mappedContact.getLocation().getCity()));
    assertThat(apiContact.getLocation().getState(), is(mappedContact.getLocation().getState()));
    assertThat(apiContact.getLocation().getStreet(), is(mappedContact.getLocation().getStreet()));
    assertThat(apiContact.getLocation().getZip(), is(mappedContact.getLocation().getZip()));
    assertThat(apiContact.getPicture().getThumbnail(), is(mappedContact.getPicture().getThumbnail()));
    assertThat(apiContact.getPicture().getLarge(), is(mappedContact.getPicture().getLarge()));
    assertThat(apiContact.getPicture().getMedium(), is(mappedContact.getPicture().getMedium()));
  }

  private ApiContact createFullContact() {
    ApiContact contact = new ApiContact();
    contact.setMd5("md5");
    contact.setCell("cell");
    contact.setEmail("email");
    contact.setGender("gender");
    contact.setPhone("phone");
    contact.setPassword("password");
    contact.setUsername("username");
    contact.setVersion("version");

    ApiName name = new ApiName();
    name.setTitle("title");
    name.setFirst("first");
    name.setLast("last");
    contact.setName(name);

    ApiLocation location = new ApiLocation();
    location.setCity("city");
    location.setState("state");
    location.setStreet("street");
    location.setZip("zip");
    contact.setLocation(location);

    ApiPicture picture = new ApiPicture();
    picture.setThumbnail("thumbnail");
    picture.setLarge("large");
    picture.setMedium("medium");
    contact.setPicture(picture);

    return contact;
  }
}
package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiContact;
import me.panavtec.cleancontacts.domain.mappers.Mapper;
import me.panavtec.cleancontacts.domain.model.Contact;

public class ApiContactMapper implements Mapper<ApiContact, Contact> {

  private static final ApiLocationMapper LOCATION_MAPPER = new ApiLocationMapper();
  private static final ApiPictureMapper PICTURE_MAPPER = new ApiPictureMapper();
  private static final ApiNameMapper NAME_MAPPER = new ApiNameMapper();

  @Override public Contact map(ApiContact model) {
    if (model == null) {
      return null;
    }
    Contact contact = new Contact();

    contact.setMd5(model.getMd5());
    contact.setCell(model.getCell());
    contact.setEmail(model.getEmail());
    contact.setGender(model.getGender());
    contact.setName(NAME_MAPPER.map(model.getName()));
    contact.setPhone(model.getPhone());
    contact.setPassword(model.getPassword());
    contact.setUsername(model.getUsername());
    contact.setVersion(model.getVersion());
    contact.setLocation(LOCATION_MAPPER.map(model.getLocation()));
    contact.setPicture(PICTURE_MAPPER.map(model.getPicture()));

    return contact;
  }
}

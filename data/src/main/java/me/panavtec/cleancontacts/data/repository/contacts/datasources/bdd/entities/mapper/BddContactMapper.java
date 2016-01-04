package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.domain.mappers.TwoWaysMapper;
import me.panavtec.cleancontacts.domain.model.Contact;

public class BddContactMapper implements TwoWaysMapper<BddContact, Contact> {

  private static final BddLocationMapper LOCATION_MAPPER = new BddLocationMapper();
  private static final BddPictureMapper PICTURE_MAPPER = new BddPictureMapper();
  private static final BddNameMapper NAME_MAPPER = new BddNameMapper();

  @Override public BddContact inverseMap(Contact model) {
    if (model == null) {
      return null;
    }
    BddContact contact = new BddContact();

    contact.setMd5(model.getMd5());
    contact.setCell(model.getCell());
    contact.setEmail(model.getEmail());
    contact.setGender(model.getGender());
    contact.setName(NAME_MAPPER.inverseMap(model.getName()));
    contact.setPhone(model.getPhone());
    contact.setPassword(model.getPassword());
    contact.setUsername(model.getUsername());
    contact.setVersion(model.getVersion());
    contact.setLocation(LOCATION_MAPPER.inverseMap(model.getLocation()));
    contact.setPicture(PICTURE_MAPPER.inverseMap(model.getPicture()));

    return contact;
  }

  @Override public Contact map(BddContact model) {
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

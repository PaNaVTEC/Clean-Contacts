package me.panavtec.cleancontacts.presentation.model.mapper;

import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.entities.Name;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.Mapper;

public class PresentationContactMapper implements Mapper<Contact, PresentationContact> {
  
  private static final PresentationLocationMapper
      PRESENTATION_LOCATION_MAPPER = new PresentationLocationMapper();
  private static final PresentationPictureMapper
      PRESENTATION_PICTURE_MAPPER = new PresentationPictureMapper();
  
  @Override public PresentationContact modelToData(Contact model) {
    if (model == null) {
      return null;
    }
    PresentationContact presentationContact = new PresentationContact();
    presentationContact.setMd5(model.getMd5());
    presentationContact.setCell(model.getCell());
    presentationContact.setEmail(model.getEmail());
    presentationContact.setGender(model.getGender());
    Name name = model.getName();
    if (name != null) {
      presentationContact.setFirstName(name.getFirst());
      presentationContact.setLastName(name.getLast());
      presentationContact.setTitle(name.getTitle());
    }
    presentationContact.setPhone(model.getPhone());
    presentationContact.setPassword(model.getPassword());
    presentationContact.setUsername(model.getUsername());
    presentationContact.setVersion(model.getVersion());
    presentationContact.setLocation(PRESENTATION_LOCATION_MAPPER.modelToData(model.getLocation()));
    presentationContact.setPicture(PRESENTATION_PICTURE_MAPPER.modelToData(model.getPicture()));
    return presentationContact;
  }

  @Override public Contact dataToModel(PresentationContact data) {
    if (data == null) {
      return null;
    }
    Contact contact = new Contact();
    contact.setMd5(data.getMd5());
    contact.setCell(data.getCell());
    contact.setEmail(data.getEmail());
    contact.setGender(data.getGender());
    Name name = new Name();
    name.setFirst(name.getFirst());
    name.setLast(name.getLast());
    name.setTitle(name.getTitle());
    contact.setName(name);
    contact.setPhone(data.getPhone());
    contact.setPassword(data.getPassword());
    contact.setUsername(data.getUsername());
    contact.setVersion(data.getVersion());
    contact.setLocation(PRESENTATION_LOCATION_MAPPER.dataToModel(data.getLocation()));
    contact.setPicture(PRESENTATION_PICTURE_MAPPER.dataToModel(data.getPicture()));
    return contact;
  }
}

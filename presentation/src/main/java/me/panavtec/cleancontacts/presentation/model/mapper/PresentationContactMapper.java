package me.panavtec.cleancontacts.presentation.model.mapper;

import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.Name;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;
import me.panavtec.cleancontacts.presentation.model.mapper.base.Mapper;

public class PresentationContactMapper implements Mapper<Contact, PresentationContact> {

  private static final PresentationLocationMapper PRESENTATION_LOCATION_MAPPER =
      new PresentationLocationMapper();
  private static final PresentationPictureMapper PRESENTATION_PICTURE_MAPPER =
      new PresentationPictureMapper();

  @Override public PresentationContact map(Contact model) {
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
    presentationContact.setLocation(PRESENTATION_LOCATION_MAPPER.map(model.getLocation()));
    presentationContact.setPicture(PRESENTATION_PICTURE_MAPPER.map(model.getPicture()));
    return presentationContact;
  }
}
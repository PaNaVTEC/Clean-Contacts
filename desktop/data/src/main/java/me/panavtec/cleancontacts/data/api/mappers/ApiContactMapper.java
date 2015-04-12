package me.panavtec.cleancontacts.data.api.mappers;

import me.panavtec.cleancontacts.data.api.entities.ApiContact;
import me.panavtec.cleancontacts.data.mappers.Mapper;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;

public class ApiContactMapper implements Mapper<Contact, ApiContact> {

  private static final ApiNameMapper apiNameMapper = new ApiNameMapper();

  @Override public ApiContact modelToData(Contact model) {
    if (model == null) {
      return null;
    }
    ApiContact contact = new ApiContact();
    contact.setCell(model.getCell());
    contact.setDob(model.getDob());
    contact.setEmail(model.getEmail());
    contact.setGender(model.getGender());
    contact.setMd5(model.getMd5());
    contact.setName(apiNameMapper.modelToData(model.getName()));
    contact.setPassword(model.getPassword());
    contact.setRegistered(model.getRegistered());
    contact.setSalt(model.getSalt());
    contact.setSha1(model.getSha1());
    contact.setSha256(model.getSha256());
    contact.setUsername(model.getUsername());
    contact.setVersion(model.getVersion());
    contact.setSSN(model.getSSN());
    contact.setPhone(model.getPhone());
    return contact;
  }

  @Override public Contact dataToModel(ApiContact data) {
    if (data == null) {
      return null;
    }
    Contact contact = new Contact();
    contact.setCell(data.getCell());
    contact.setDob(data.getDob());
    contact.setEmail(data.getEmail());
    contact.setGender(data.getGender());
    contact.setMd5(data.getMd5());
    contact.setName(apiNameMapper.dataToModel(data.getName()));
    contact.setPassword(data.getPassword());
    contact.setRegistered(data.getRegistered());
    contact.setSalt(data.getSalt());
    contact.setSha1(data.getSha1());
    contact.setSha256(data.getSha256());
    contact.setUsername(data.getUsername());
    contact.setVersion(data.getVersion());
    contact.setSSN(data.getSSN());
    contact.setPhone(data.getPhone());
    return contact;
  }
}

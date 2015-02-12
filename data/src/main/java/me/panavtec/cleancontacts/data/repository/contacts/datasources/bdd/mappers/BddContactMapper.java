package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.mappers;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.domain.entities.abstractmappers.Mapper;

public class BddContactMapper implements Mapper<Contact, BddContact> {

    private static final BddLocationMapper locationMapper = new BddLocationMapper();
    private static final BddNameMapper nameMapper = new BddNameMapper();
    private static final BddPictureMapper pictureMapper = new BddPictureMapper();

    @Override public BddContact modelToData(Contact model) {
        if (model == null) {
            return null;
        }
        BddContact contact = new BddContact();
        contact.setCell(model.getCell());
        contact.setDob(model.getDob());
        contact.setEmail(model.getEmail());
        contact.setGender(model.getGender());
        contact.setLocation(locationMapper.modelToData(model.getLocation()));
        contact.setMd5(model.getMd5());
        contact.setName(nameMapper.modelToData(model.getName()));
        contact.setPassword(model.getPassword());
        contact.setPicture(pictureMapper.modelToData(model.getPicture()));
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

    @Override public Contact dataToModel(BddContact data) {
        if (data == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setCell(data.getCell());
        contact.setDob(data.getDob());
        contact.setEmail(data.getEmail());
        contact.setGender(data.getGender());
        contact.setLocation(locationMapper.dataToModel(data.getLocation()));
        contact.setMd5(data.getMd5());
        contact.setName(nameMapper.dataToModel(data.getName()));
        contact.setPassword(data.getPassword());
        contact.setPicture(pictureMapper.dataToModel(data.getPicture()));
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

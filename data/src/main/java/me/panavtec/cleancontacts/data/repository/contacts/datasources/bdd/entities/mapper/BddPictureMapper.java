package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddPicture;
import me.panavtec.cleancontacts.domain.mappers.TwoWaysMapper;
import me.panavtec.cleancontacts.domain.model.Picture;

public class BddPictureMapper implements TwoWaysMapper<BddPicture, Picture> {
  @Override public BddPicture inverseMap(Picture model) {
    if (model == null) {
      return null;
    }
    BddPicture picture = new BddPicture();
    picture.setLarge(model.getLarge());
    picture.setMedium(model.getMedium());
    picture.setThumbnail(model.getThumbnail());
    return picture;
  }

  @Override public Picture map(BddPicture model) {
    if (model == null) {
      return null;
    }
    Picture picture = new Picture();
    picture.setLarge(model.getLarge());
    picture.setMedium(model.getMedium());
    picture.setThumbnail(model.getThumbnail());
    return picture;
  }
}

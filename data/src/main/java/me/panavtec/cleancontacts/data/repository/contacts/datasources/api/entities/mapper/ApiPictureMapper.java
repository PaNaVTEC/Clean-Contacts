package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiPicture;
import me.panavtec.cleancontacts.domain.mappers.Mapper;
import me.panavtec.cleancontacts.domain.model.Picture;

public class ApiPictureMapper implements Mapper<ApiPicture, Picture> {
  @Override public Picture map(ApiPicture model) {
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

package me.panavtec.cleancontacts.presentation.model.mapper;

import me.panavtec.cleancontacts.domain.model.Picture;
import me.panavtec.cleancontacts.presentation.model.PresentationPicture;
import me.panavtec.cleancontacts.presentation.model.mapper.base.Mapper;

public class PresentationPictureMapper implements Mapper<Picture, PresentationPicture> {
  @Override public PresentationPicture modelToData(Picture model) {
    if (model == null) {
      return null;
    }
    PresentationPicture picture = new PresentationPicture();
    picture.setLarge(model.getLarge());
    picture.setMedium(model.getMedium());
    picture.setThumbnail(model.getThumbnail());
    return picture;
  }

  @Override public Picture dataToModel(PresentationPicture model) {
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

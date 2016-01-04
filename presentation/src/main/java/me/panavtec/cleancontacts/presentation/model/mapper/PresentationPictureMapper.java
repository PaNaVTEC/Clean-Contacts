package me.panavtec.cleancontacts.presentation.model.mapper;

import me.panavtec.cleancontacts.domain.model.Picture;
import me.panavtec.cleancontacts.presentation.model.PresentationPicture;
import me.panavtec.cleancontacts.domain.mappers.Mapper;

public class PresentationPictureMapper implements Mapper<Picture, PresentationPicture> {
  @Override public PresentationPicture map(Picture model) {
    if (model == null) {
      return null;
    }
    PresentationPicture picture = new PresentationPicture();
    picture.setLarge(model.getLarge());
    picture.setMedium(model.getMedium());
    picture.setThumbnail(model.getThumbnail());
    return picture;
  }
}

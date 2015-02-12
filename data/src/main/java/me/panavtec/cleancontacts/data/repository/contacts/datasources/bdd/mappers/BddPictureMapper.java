package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.mappers;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddPicture;
import me.panavtec.cleancontacts.domain.entities.Picture;
import me.panavtec.cleancontacts.domain.entities.abstractmappers.Mapper;

public class BddPictureMapper implements Mapper<Picture, BddPicture> {

    @Override public BddPicture modelToData(Picture model) {
        if (model == null) {
            return null;
        }
        BddPicture picture = new BddPicture();
        picture.setLarge(model.getLarge());
        picture.setMedium(model.getMedium());
        picture.setThumbnail(model.getThumbnail());
        return picture;
    }

    @Override public Picture dataToModel(BddPicture data) {
        if (data == null) {
            return null;
        }
        Picture picture = new Picture();
        picture.setLarge(data.getLarge());
        picture.setMedium(data.getMedium());
        picture.setThumbnail(data.getThumbnail());
        return picture;
    }
    
}

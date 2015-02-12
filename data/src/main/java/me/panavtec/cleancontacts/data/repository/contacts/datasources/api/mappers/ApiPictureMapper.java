package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.mappers;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiPicture;
import me.panavtec.cleancontacts.domain.entities.Picture;
import me.panavtec.cleancontacts.domain.entities.abstractmappers.Mapper;

public class ApiPictureMapper implements Mapper<Picture, ApiPicture> {

    @Override public ApiPicture modelToData(Picture model) {
        if (model == null) {
            return null;
        }
        ApiPicture apiPicture = new ApiPicture();
        apiPicture.setLarge(model.getLarge());
        apiPicture.setMedium(model.getMedium());
        apiPicture.setThumbnail(model.getThumbnail());
        return apiPicture;
    }

    @Override public Picture dataToModel(ApiPicture data) {
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

package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.mappers;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiLocation;
import me.panavtec.cleancontacts.domain.entities.Location;
import me.panavtec.cleancontacts.domain.entities.abstractmappers.Mapper;

public class ApiLocationMapper implements Mapper<Location, ApiLocation> {

    @Override public ApiLocation modelToData(Location model) {
        if (model == null) {
            return null;
        }
        ApiLocation apiLocation = new ApiLocation();
        apiLocation.setCity(model.getCity());
        apiLocation.setState(model.getState());
        apiLocation.setStreet(model.getStreet());
        apiLocation.setZip(model.getZip());
        return apiLocation;
    }

    @Override public Location dataToModel(ApiLocation data) {
        if (data == null) {
            return null;
        }
        Location location = new Location();
        location.setCity(data.getCity());
        location.setState(data.getState());
        location.setStreet(data.getStreet());
        location.setZip(data.getZip());
        return location;
    }
}

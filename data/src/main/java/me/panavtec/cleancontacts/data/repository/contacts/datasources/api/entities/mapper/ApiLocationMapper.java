package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiLocation;
import me.panavtec.cleancontacts.domain.mappers.Mapper;
import me.panavtec.cleancontacts.domain.model.Location;

public class ApiLocationMapper implements Mapper<ApiLocation, Location> {
  @Override public Location map(ApiLocation model) {
    if (model == null) {
      return null;
    }
    Location location = new Location();
    location.setCity(model.getCity());
    location.setState(model.getState());
    location.setStreet(model.getStreet());
    location.setZip(model.getZip());
    return location;
  }
}

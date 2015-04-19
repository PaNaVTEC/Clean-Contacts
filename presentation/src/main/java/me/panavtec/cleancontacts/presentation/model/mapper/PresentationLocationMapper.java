package me.panavtec.cleancontacts.presentation.model.mapper;

import me.panavtec.cleancontacts.presentation.model.PresentationLocation;
import me.panavtec.cleancontacts.presentation.model.mapper.base.Mapper;
import me.panavtec.cleancontacts.domain.entities.Location;

public class PresentationLocationMapper implements Mapper<Location, PresentationLocation> {
  @Override public PresentationLocation modelToData(Location model) {
    if (model == null) {
      return null;
    }
    PresentationLocation location = new PresentationLocation();
    location.setCity(model.getCity());
    location.setState(model.getState());
    location.setStreet(model.getStreet());
    location.setZip(model.getZip());
    return location;
  }

  @Override public Location dataToModel(PresentationLocation model) {
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

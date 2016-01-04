package me.panavtec.cleancontacts.presentation.model.mapper;

import me.panavtec.cleancontacts.domain.model.Location;
import me.panavtec.cleancontacts.presentation.model.PresentationLocation;
import me.panavtec.cleancontacts.presentation.model.mapper.base.Mapper;

public class PresentationLocationMapper implements Mapper<Location, PresentationLocation> {
  @Override public PresentationLocation map(Location model) {
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
}

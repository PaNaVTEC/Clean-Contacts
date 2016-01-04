package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddLocation;
import me.panavtec.cleancontacts.domain.mappers.TwoWaysMapper;
import me.panavtec.cleancontacts.domain.model.Location;

public class BddLocationMapper implements TwoWaysMapper<BddLocation, Location> {

  @Override public BddLocation inverseMap(Location model) {
    if (model == null) {
      return null;
    }
    BddLocation location = new BddLocation();
    location.setCity(model.getCity());
    location.setState(model.getState());
    location.setStreet(model.getStreet());
    location.setZip(model.getZip());
    return location;
  }

  @Override public Location map(BddLocation model) {
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

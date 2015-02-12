package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.mappers;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddLocation;
import me.panavtec.cleancontacts.domain.entities.Location;
import me.panavtec.cleancontacts.domain.entities.abstractmappers.Mapper;

public class BddLocationMapper implements Mapper<Location, BddLocation> {

    @Override public BddLocation modelToData(Location model) {
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

    @Override public Location dataToModel(BddLocation data) {
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

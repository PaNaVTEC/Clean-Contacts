package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.mappers;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddName;
import me.panavtec.cleancontacts.domain.entities.Name;
import me.panavtec.cleancontacts.domain.entities.abstractmappers.Mapper;

public class BddNameMapper implements Mapper<Name, BddName> {

    @Override public BddName modelToData(Name model) {
        if (model == null) {
            return null;
        }
        BddName name = new BddName();
        name.setFirst(model.getFirst());
        name.setLast(model.getLast());
        name.setTitle(model.getTitle());
        return name;
    }

    @Override public Name dataToModel(BddName data) {
        if (data == null) {
            return null;
        }
        Name name = new Name();
        name.setFirst(data.getFirst());
        name.setLast(data.getLast());
        name.setTitle(data.getTitle());
        return name;
    }
    
}

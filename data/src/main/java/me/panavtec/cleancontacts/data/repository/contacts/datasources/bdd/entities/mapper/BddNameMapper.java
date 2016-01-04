package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddName;
import me.panavtec.cleancontacts.domain.mappers.TwoWaysMapper;
import me.panavtec.cleancontacts.domain.model.Name;

public class BddNameMapper implements TwoWaysMapper<BddName, Name> {

  @Override public BddName inverseMap(Name model) {
    if (model == null) {
      return null;
    }
    BddName name = new BddName();
    name.setTitle(model.getTitle());
    name.setFirst(model.getFirst());
    name.setLast(model.getLast());
    return name;
  }

  @Override public Name map(BddName model) {
    if (model == null) {
      return null;
    }
    Name name = new Name();
    name.setTitle(model.getTitle());
    name.setFirst(model.getFirst());
    name.setLast(model.getLast());
    return name;
  }

}

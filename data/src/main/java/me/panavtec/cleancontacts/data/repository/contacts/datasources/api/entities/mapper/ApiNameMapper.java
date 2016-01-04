package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.mapper;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiName;
import me.panavtec.cleancontacts.domain.mappers.Mapper;
import me.panavtec.cleancontacts.domain.model.Name;

public class ApiNameMapper implements Mapper<ApiName, Name> {
  @Override public Name map(ApiName model) {
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

package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.mappers;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiName;
import me.panavtec.cleancontacts.domain.entities.Name;
import me.panavtec.cleancontacts.domain.entities.abstractmappers.Mapper;

public class ApiNameMapper implements Mapper<Name, ApiName> {

    @Override public ApiName modelToData(Name model) {
        if (model == null) {
            return null;
        }
        ApiName apiName = new ApiName();
        apiName.setFirst(model.getFirst());
        apiName.setLast(model.getLast());
        apiName.setTitle(model.getTitle());
        return apiName;
    }

    @Override public Name dataToModel(ApiName data) {
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

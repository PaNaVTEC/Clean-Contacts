package me.panavtec.cleancontacts.domain.entities.abstractmappers;

public interface Mapper<M, P> {
    P modelToData(M model);
    M dataToModel(P data);
}

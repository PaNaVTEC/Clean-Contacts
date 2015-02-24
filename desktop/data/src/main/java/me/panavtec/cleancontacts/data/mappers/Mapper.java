package me.panavtec.cleancontacts.data.mappers;

public interface Mapper<M, D> {
    public D modelToData(M model);
    public M dataToModel(D data);
}

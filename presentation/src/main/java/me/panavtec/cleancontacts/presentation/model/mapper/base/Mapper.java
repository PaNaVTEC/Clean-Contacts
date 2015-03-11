package me.panavtec.cleancontacts.presentation.model.mapper.base;

public interface Mapper<M, P> {
  P modelToData(M model);
  M dataToModel(P data);
}


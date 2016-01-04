package me.panavtec.cleancontacts.presentation.model.mapper.base;

public interface Mapper<M, P> {
  P map(M model);
}


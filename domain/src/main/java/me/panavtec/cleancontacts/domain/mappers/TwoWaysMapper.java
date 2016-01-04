package me.panavtec.cleancontacts.domain.mappers;

public interface TwoWaysMapper<M, P> extends Mapper<M, P> {
  M inverseMap(P model);
}
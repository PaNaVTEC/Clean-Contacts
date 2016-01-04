package me.panavtec.cleancontacts.domain.mappers;

import java.util.ArrayList;
import java.util.List;

public class ListMapper<M, P> implements Mapper<List<M>, List<P>> {

  private final Mapper<M, P> mapper;

  public ListMapper(Mapper<M, P> mapper) {
    this.mapper = mapper;
  }

  @Override public List<P> map(List<M> models) {
    ArrayList<P> persistences = new ArrayList<>();
    if (models != null && models.size() > 0) {
      for (M model : models) {
        persistences.add(mapper.map(model));
      }
    }
    return persistences;
  }
}

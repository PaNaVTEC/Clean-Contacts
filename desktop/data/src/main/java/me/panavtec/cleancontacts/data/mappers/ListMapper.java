package me.panavtec.cleancontacts.data.mappers;

import java.util.ArrayList;
import java.util.List;

public class ListMapper<M, P> implements Mapper<List<M>, List<P>> {

  private Mapper<M, P> mapper;

  public ListMapper(Mapper<M, P> mapper) {
    this.mapper = mapper;
  }

  @Override public List<P> modelToData(List<M> models) {
    ArrayList<P> persistences = new ArrayList<>();
    if (models != null && models.size() > 0) {
      for (M model : models) {
        persistences.add(mapper.modelToData(model));
      }
    }
    return persistences;
  }

  @Override public List<M> dataToModel(List<P> persistences) {
    ArrayList<M> models = new ArrayList<>();
    if (persistences != null && persistences.size() > 0) {
      for (P persistence : persistences) {
        models.add(mapper.dataToModel(persistence));
      }
    }
    return models;
  }
}

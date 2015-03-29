package me.panavtec.cleancontacts.domain.interactors.base;

public interface ThreadSpec {
  <T> void execute(Action<T> action, T result);
}

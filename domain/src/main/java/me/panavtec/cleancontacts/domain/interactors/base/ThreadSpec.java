package me.panavtec.cleancontacts.domain.interactors.base;

public interface ThreadSpec {
  <T> void execute(Action<T> runnable, T result);
}

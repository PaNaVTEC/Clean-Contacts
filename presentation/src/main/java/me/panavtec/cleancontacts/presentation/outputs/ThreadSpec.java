package me.panavtec.cleancontacts.presentation.outputs;

public interface ThreadSpec {
  <T> void execute(Action<T> action, T result);
}

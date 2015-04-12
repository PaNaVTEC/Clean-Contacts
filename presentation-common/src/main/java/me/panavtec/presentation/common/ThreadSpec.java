package me.panavtec.presentation.common;

public interface ThreadSpec {
  <T> void execute(Action<T> action, T result);
}

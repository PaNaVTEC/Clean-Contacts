package me.panavtec.cleancontacts.domain.interactors.base;

public class TestThreadSpec implements ThreadSpec {
  @Override public <T> void execute(Action<T> action, T result) {
    action.onAction(result);
  }
}

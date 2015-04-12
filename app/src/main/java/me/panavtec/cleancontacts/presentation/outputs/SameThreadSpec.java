package me.panavtec.cleancontacts.presentation.outputs;

public class SameThreadSpec implements ThreadSpec {

  @Override public <T> void execute(Action<T> action, T result) {
    action.onAction(result);
  }
}

package me.panavtec.cleancontacts.presentation.outputs.interactors.base;

import me.panavtec.cleancontacts.presentation.outputs.Action;
import me.panavtec.cleancontacts.presentation.outputs.ThreadSpec;

public class TestThreadSpec implements ThreadSpec {
  @Override public <T> void execute(Action<T> action, T result) {
    action.onAction(result);
  }
}

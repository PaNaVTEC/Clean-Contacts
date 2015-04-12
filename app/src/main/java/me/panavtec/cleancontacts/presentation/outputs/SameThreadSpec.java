package me.panavtec.cleancontacts.presentation.outputs;

import me.panavtec.presentation.common.Action;
import me.panavtec.presentation.common.ThreadSpec;

public class SameThreadSpec implements ThreadSpec {

  @Override public <T> void execute(Action<T> action, T result) {
    action.onAction(result);
  }
}

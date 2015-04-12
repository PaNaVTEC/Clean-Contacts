package me.panavtec.cleancontacts.presentation.outputs;

import me.panavtec.presentation.common.Action;
import me.panavtec.presentation.common.ThreadSpec;

public class BackThreadSpec implements ThreadSpec {
  @Override public <T> void execute(final Action<T> action, final T result) {
    new Runnable() {
      @Override public void run() {
        action.onAction(result);
      }
    }.run();
  }
}

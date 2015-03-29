package me.panavtec.cleancontacts.domain;

import me.panavtec.cleancontacts.domain.interactors.base.Action;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;

public class BackThreadSpec implements ThreadSpec {
  @Override public <T> void execute(final Action<T> action, final T result) {
    new Runnable() {
      @Override public void run() {
        action.onAction(result);
      }
    }.run();
  }
}

package me.panavtec.cleancontacts.desktop.domain;

import me.panavtec.cleancontacts.presentation.outputs.Action;
import me.panavtec.cleancontacts.presentation.outputs.ThreadSpec;

public class SameThreadSpec implements ThreadSpec {
  @Override public <T> void execute(Action<T> action, T result) {
    action.onAction(result);
  }
}

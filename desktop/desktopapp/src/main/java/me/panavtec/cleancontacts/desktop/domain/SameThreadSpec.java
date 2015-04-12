package me.panavtec.cleancontacts.desktop.domain;

import me.panavtec.cleancontacts.presentation.outputs.interactors.base.Action;
import me.panavtec.cleancontacts.presentation.outputs.interactors.base.ThreadSpec;

public class SameThreadSpec implements ThreadSpec {
  @Override public <T> void execute(Action<T> action, T result) {
    action.onAction(result);
  }
}

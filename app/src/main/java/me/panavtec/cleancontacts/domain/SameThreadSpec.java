package me.panavtec.cleancontacts.domain;

import me.panavtec.cleancontacts.domain.interactors.base.Action;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;

public class SameThreadSpec implements ThreadSpec {

  @Override public <T> void execute(Action<T> action, T result) {
    action.onAction(result);
  }
}

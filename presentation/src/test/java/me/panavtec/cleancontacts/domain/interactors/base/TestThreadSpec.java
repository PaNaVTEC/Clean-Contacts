package me.panavtec.cleancontacts.domain.interactors.base;

import me.panavtec.threaddecoratedview.views.ThreadSpec;

public class TestThreadSpec implements ThreadSpec {

  @Override public void execute(Runnable action) {
    action.run();
  }
}

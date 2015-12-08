package me.panavtec.cleancontacts.presentation.interactors.base;

import me.panavtec.threaddecoratedview.views.ThreadSpec;

public class TestThreadSpec implements ThreadSpec {

  @Override public void execute(Runnable action) {
    action.run();
  }
}

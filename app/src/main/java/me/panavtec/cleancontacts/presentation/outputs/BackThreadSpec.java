package me.panavtec.cleancontacts.presentation.outputs;

import me.panavtec.presentation.common.ThreadSpec;

public class BackThreadSpec implements ThreadSpec {

  @Override public void execute(Runnable action) {
    new Thread(action).start();
  }
}

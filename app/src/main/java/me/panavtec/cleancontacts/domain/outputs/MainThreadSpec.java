package me.panavtec.cleancontacts.domain.outputs;

import android.os.Handler;
import me.panavtec.presentation.common.ThreadSpec;

public class MainThreadSpec implements ThreadSpec {

  Handler handler = new Handler();

  @Override public void execute(Runnable action) {
    handler.post(action);
  }
}

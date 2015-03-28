package me.panavtec.cleancontacts.domain;

import android.os.Handler;
import me.panavtec.cleancontacts.domain.interactors.base.Action;
import me.panavtec.cleancontacts.domain.interactors.base.ThreadSpec;

public class MainThreadSpec implements ThreadSpec {

  Handler handler = new Handler();

  @Override public <T> void execute(final Action<T> action, final T result) {
    handler.post(new Runnable() {
      @Override public void run() {
        action.onAction(result);
      }
    });
  }
}

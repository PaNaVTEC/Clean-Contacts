package me.panavtec.cleancontacts.domain;

import android.os.Handler;
import android.os.Looper;
import de.greenrobot.event.EventBus;
import me.panavtec.cleancontacts.domain.abstractions.Bus;

public class BusImp extends EventBus implements Bus {

  private static final Handler handler = new Handler(Looper.getMainLooper());

  @Override public void post(final Object event) {
    handler.post(new Runnable() {
      @Override public void run() {
        BusImp.super.post(event);
      }
    });
  }
}

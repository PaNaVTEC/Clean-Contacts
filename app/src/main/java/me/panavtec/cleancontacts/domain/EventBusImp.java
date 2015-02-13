package me.panavtec.cleancontacts.domain;

import android.os.Handler;
import android.os.Looper;

public class EventBusImp extends de.greenrobot.event.EventBus implements EventBus {

    private static final Handler handler = new Handler(Looper.getMainLooper());

    @Override public void post(final Object event) {
        handler.post(new Runnable() {
            @Override public void run() {
                EventBusImp.super.post(event);
            }
        });
    }
}

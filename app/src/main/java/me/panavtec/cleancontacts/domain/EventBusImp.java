package me.panavtec.cleancontacts.domain;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class EventBusImp extends Bus implements EventBus {

    private static final Handler handler = new Handler(Looper.getMainLooper());

    public EventBusImp() {
        super(ThreadEnforcer.ANY);
    }

    @Override public void post(final Object event) {
        handler.post(new Runnable() {
            @Override public void run() {
                EventBusImp.super.post(event);
            }
        });
    }

}

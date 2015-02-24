package me.panavtec.cleancontacts.desktop.domain;

import me.panavtec.cleancontacts.domain.abstractions.Bus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BusImp implements Bus {

    private static final List<Object> subscribed = new ArrayList<>();

    @Override public void post(Object object) {
        for (Object receiver : subscribed) {
            try {
                Method onEvent = receiver.getClass().getMethod("onEvent", object.getClass());
                onEvent.invoke(receiver, object);
            } catch (Throwable e) {
                // ignore
            }
        }
    }

    @Override public void register(Object object) {
        subscribed.add(object);
    }

    @Override public void unregister(Object object) {
        subscribed.remove(object);
    }
}

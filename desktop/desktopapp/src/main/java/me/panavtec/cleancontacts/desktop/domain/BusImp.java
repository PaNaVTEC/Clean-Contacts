package me.panavtec.cleancontacts.desktop.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import me.panavtec.cleancontacts.domain.abstractions.Bus;

public class BusImp implements Bus {

  private static final List<Object> subscribed = new ArrayList<>();

  @Override public void post(final Object object) {
    for (final Object receiver : subscribed) {
      try {
        final Method onEvent = receiver.getClass().getMethod("onEvent", object.getClass());
        Platform.runLater(new Runnable() {
          @Override public void run() {
            try {
              onEvent.invoke(receiver, object);
            } catch (IllegalAccessException | InvocationTargetException e) {
              e.printStackTrace();
            }
          }
        });
      } catch (Throwable e) {
        e.printStackTrace();
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

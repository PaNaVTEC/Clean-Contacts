package me.panavtec.cleancontacts.presentation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import me.panavtec.presentation.common.ThreadSpec;

public abstract class Presenter<V extends PresenterView> {

  private V view;
  private Invoka proxyCreate;
  private ThreadSpec mainThreadSpec;

  public Presenter(ThreadSpec mainThreadSpec) {
    this.mainThreadSpec = mainThreadSpec;
  }

  public void attachView(V view) {
    this.view = proxyView(view);
  }

  private <V> V proxyView(final V view) {
    Class<?>[] interfaces = view.getClass().getInterfaces();
    for (Class<?> interf : interfaces) {
      if (interf.getSuperclass().equals(PresenterView.class)) {
        proxyCreate = new Invoka<>(view, mainThreadSpec);
        return (V) Proxy.newProxyInstance(interf.getClassLoader(), new Class[] { interf },
            proxyCreate);
      }
    }
    return null;
  }

  public void detachView() {
    proxyCreate = null;
    view = null;
  }

  public V getView() {
    return view;
  }

  static class Invoka<V> implements InvocationHandler {
    private V view;
    private ThreadSpec threadSpec;

    public Invoka(V view, ThreadSpec threadSpec) {
      this.view = view;
      this.threadSpec = threadSpec;
    }

    @Override public Object invoke(Object proxy, final Method method, final Object[] args)
        throws Throwable {
      threadSpec.execute(new Runnable() {
        @Override public void run() {
          try {
            method.invoke(view, args);
          } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
          }
        }
      });
      return null;
    }
  }
}

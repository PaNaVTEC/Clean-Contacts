package me.panavtec.presentation.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InteractorOutputInjector {

  private static final String INJECTOR_SUFFIX = "_OutputInjector";

  public static <T> void inject(T source) {
    try {
      Class<?> container = getInjectorContainerClass(source.getClass());
      Class<?> injector = Class.forName(container.getCanonicalName() + INJECTOR_SUFFIX);
      Method inject = injector.getMethod("injectOutputs", container);
      inject.invoke(null, source);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static Class<?> getInjectorContainerClass(Class<?> sourceClass) {
    try {
      Class.forName(sourceClass.getName() + INJECTOR_SUFFIX);
      return Class.forName(sourceClass.getName());
    } catch (ClassNotFoundException e) {
      return getInjectorContainerClass(sourceClass.getSuperclass());
    } catch (Throwable e) {
      return null;
    }
  }
}

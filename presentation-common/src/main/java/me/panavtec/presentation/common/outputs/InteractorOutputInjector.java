package me.panavtec.presentation.common.outputs;

import java.lang.reflect.Method;

public class InteractorOutputInjector {

  private static final String CLASS_SUFFIX = "_OutputInjector";
  private static final String METHOD_NAME = "injectOutputs";

  public static <T> void inject(T source) {
    try {
      Class<?> container = getInjectorContainerClass(source.getClass());
      Class<?> injector = Class.forName(container.getCanonicalName() + CLASS_SUFFIX);
      Method inject = injector.getMethod(METHOD_NAME, container);
      inject.invoke(null, source);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Class<?> getInjectorContainerClass(Class<?> sourceClass) {
    try {
      return Class.forName(sourceClass.getName());
    } catch (ClassNotFoundException e) {
      return getInjectorContainerClass(sourceClass.getSuperclass());
    } catch (Exception e) {
      return null;
    }
  }
}

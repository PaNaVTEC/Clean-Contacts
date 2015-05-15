package me.panavtec.presentation.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class DefaultInjector {

  private String classSuffix;
  private String methodName;

  public DefaultInjector(String classSuffix, String methodName) {
    this.classSuffix = classSuffix;
    this.methodName = methodName;
  }

  public <T> void inject(T source) {
    try {
      Class<?> container = getInjectorContainerClass(source.getClass());
      Class<?> injector = Class.forName(container.getCanonicalName() + classSuffix);
      Method inject = injector.getMethod(methodName, container);
      inject.invoke(null, source);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private Class<?> getInjectorContainerClass(Class<?> sourceClass) {
    try {
      Class.forName(sourceClass.getName() + classSuffix);
      return Class.forName(sourceClass.getName());
    } catch (ClassNotFoundException e) {
      return getInjectorContainerClass(sourceClass.getSuperclass());
    } catch (Throwable e) {
      return null;
    }
  }
}

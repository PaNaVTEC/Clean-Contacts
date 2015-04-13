package me.panavtec.presentation.common.views;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import me.panavtec.presentation.common.ThreadSpec;

public class ViewInjector {
  private static final String CLASS_PREFIX = "Decorated";

  public static <T> T inject(T source, ThreadSpec mainThreadSpec) {
    try {
      Class<?> viewInterface = source.getClass().getInterfaces()[0];
      String packageName = viewInterface.getPackage().getName();
      String className = packageName + "." + CLASS_PREFIX + viewInterface.getSimpleName();
      Class<?> decoratedClass = Class.forName(className);
      Constructor<?> constructor = decoratedClass.getConstructor(viewInterface, ThreadSpec.class);
      return (T) constructor.newInstance(source, mainThreadSpec);
    } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
        InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }
}

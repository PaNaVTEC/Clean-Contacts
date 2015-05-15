package me.panavtec.presentation.common.views;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import me.panavtec.presentation.common.ThreadSpec;

public class ViewInjector {
  private static final String CLASS_PREFIX = "Decorated";

  public static <T> T inject(T source, Object presenter, ThreadSpec mainThreadSpec) {
    try {
      Class<?> expectedType = getPresenterGenericClass(presenter);
      Class<?>[] viewInterfaces = source.getClass().getInterfaces();
      for (Class<?> viewInterface : viewInterfaces) {
        if (expectedType.getCanonicalName().equals(viewInterface.getCanonicalName())) {
          String packageName = viewInterface.getPackage().getName();
          String className = packageName + "." + CLASS_PREFIX + viewInterface.getSimpleName();
          Class<?> decoratedClass = Class.forName(className);
          Constructor<?> constructor =
              decoratedClass.getConstructor(viewInterface, ThreadSpec.class);
          return (T) constructor.newInstance(source, mainThreadSpec);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static Class<?> getPresenterGenericClass(Object presenter) {
    return (Class<?>) ((ParameterizedType) presenter.getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0];
  }
}

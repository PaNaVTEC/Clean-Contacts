package me.panavtec.presentation.common.views;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import me.panavtec.presentation.Presenter;
import me.panavtec.presentation.common.ThreadSpec;

public class ViewInjector {
  private static final String CLASS_PREFIX = "Decorated";

  public static <T> T inject(T source, Object presenter, ThreadSpec mainThreadSpec) {
    return injectView(source, source.getClass(), getPresenterGenericClass(presenter),
        mainThreadSpec);
  }

  private static <T> T injectView(T source, Class<?> sourceClass, Class<?> expectedType,
      ThreadSpec mainThreadSpec) {
    try {
      Class<?>[] viewInterfaces = sourceClass.getInterfaces();
      for (Class<?> viewInterface : viewInterfaces) {
        if (isSameClass(expectedType, viewInterface)) {
          String packageName = viewInterface.getPackage().getName();
          String className = packageName + "." + CLASS_PREFIX + viewInterface.getSimpleName();
          Class<?> decoratedClass = Class.forName(className);
          Constructor<?> constructor =
              decoratedClass.getConstructor(viewInterface, ThreadSpec.class);
          return (T) constructor.newInstance(source, mainThreadSpec);
        }
      }

      Class<?> viewSuperClass = source.getClass().getSuperclass();
      if (!isSameClass(viewSuperClass, Object.class)) {
        return injectView(source, viewSuperClass, expectedType, mainThreadSpec);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static Class<?> getPresenterGenericClass(Object presenter) {
    Class<?> realPresenterClass = getSuperClassThatExtendsFromPresenter(presenter.getClass());
    return (Class<?>) ((ParameterizedType) realPresenterClass.getGenericSuperclass()).getActualTypeArguments()[0];
  }

  private static Class<?> getSuperClassThatExtendsFromPresenter(Class<?> sourceClass) {
    try {
      if (isSameClass(sourceClass.getSuperclass(), Presenter.class)) {
        return sourceClass;
      } else if (!isSameClass(sourceClass.getSuperclass(), Object.class)) {
        return getSuperClassThatExtendsFromPresenter(sourceClass.getSuperclass());
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return null;
  }

  private static boolean isSameClass(Class<?> class1, Class<?> class2) {
    return class1.getCanonicalName().equals(class2.getCanonicalName());
  }
}

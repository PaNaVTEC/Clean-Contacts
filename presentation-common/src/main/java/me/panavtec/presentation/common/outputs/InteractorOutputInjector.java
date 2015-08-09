package me.panavtec.presentation.common.outputs;

import java.lang.reflect.Method;
import me.panavtec.presentation.common.outputs.qualifiers.Output;

public class InteractorOutputInjector {

  private static final String CLASS_SUFFIX = "_OutputInjector";
  private static final String METHOD_NAME = "injectOutputs";

  public static <T> void inject(T source) {
    try {
      Class<?> container = getInjectorContainerClass(source.getClass());
      Class<?> injector = composeInjectorClass(container);
      if (injector != Void.TYPE) {
        Method inject = injector.getMethod(METHOD_NAME, container);
        inject.invoke(null, source);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Class<?> composeInjectorClass(Class<?> container) {
    String composedClassName = container.getCanonicalName() + CLASS_SUFFIX;
    try {
      return Class.forName(composedClassName);
    } catch (ClassNotFoundException e) {
      System.err.println(
          String.format("Class %s not found. Have you annotated any @%s fields in %s?",
              composedClassName, Output.class.getSimpleName(), container.getCanonicalName()));
    }
    return Void.TYPE;
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

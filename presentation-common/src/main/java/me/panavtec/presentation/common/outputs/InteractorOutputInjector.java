package me.panavtec.presentation.common.outputs;

import me.panavtec.presentation.common.DefaultInjector;

public class InteractorOutputInjector {
  public static <T> void inject(T source) {
    new DefaultInjector("_OutputInjector", "injectOutputs").inject(source);
  }
}

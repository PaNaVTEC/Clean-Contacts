package me.panavtec.presentation.compiler.proxyviews;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

public class ViewAnnotationProcessor extends AbstractProcessor {
  
  @Override public boolean process(Set<? extends TypeElement> annotations,
      RoundEnvironment roundEnv) {

    return false;
  }
}

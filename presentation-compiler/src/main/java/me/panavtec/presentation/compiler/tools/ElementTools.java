package me.panavtec.presentation.compiler.tools;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class ElementTools {

  public boolean isField(Element e) {
    return e.getKind() == ElementKind.FIELD;
  }

  public boolean isMethod(Element e) {
    return e.getKind() == ElementKind.METHOD;
  }

  public String getFieldName(Element e) {
    return e.getSimpleName().toString();
  }

  public String getElementParentCompleteClassName(Element e) {
    return e.getEnclosingElement().toString();
  }

  public String getParentElementClassName(Element e) {
    String parentClassName = getElementParentCompleteClassName(e);
    return parentClassName.substring(parentClassName.lastIndexOf('.') + 1,
        parentClassName.length());
  }

  public String getElementClassName(Element e) {
    String parentClassName = e.toString();
    return parentClassName.substring(parentClassName.lastIndexOf('.') + 1,
        parentClassName.length());
  }

  public String getParentElementPackagename(Element e) {
    String parentClassName = getElementParentCompleteClassName(e);
    return parentClassName.substring(0, parentClassName.lastIndexOf('.'));
  }
  
  public String getElementPackagename(Element e) {
    String className = e.toString();
    return className.substring(0, className.lastIndexOf('.'));
  }

  public String getElementType(Element e) {
    return e.asType().toString();
  }
}

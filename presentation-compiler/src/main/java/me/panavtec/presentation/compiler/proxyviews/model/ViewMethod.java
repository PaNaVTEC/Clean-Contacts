package me.panavtec.presentation.compiler.proxyviews.model;

import java.util.ArrayList;
import java.util.List;
import javax.lang.model.type.TypeMirror;

public class ViewMethod {
  
  private TypeMirror returnType;
  private String methodName;
  private List<TypeMirror> parameters = new ArrayList<>();
  private boolean decorate;

  public TypeMirror getReturnType() {
    return returnType;
  }

  public void setReturnType(TypeMirror returnType) {
    this.returnType = returnType;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public List<TypeMirror> getParameters() {
    return parameters;
  }

  public void setParameters(List<TypeMirror> parameters) {
    this.parameters = parameters;
  }

  public boolean isDecorate() {
    return decorate;
  }

  public void setDecorate(boolean decorate) {
    this.decorate = decorate;
  }

  @Override public String toString() {
    return "ViewMethod{" +
        "returnType=" + returnType +
        ", methodName='" + methodName + '\'' +
        ", parameters=" + parameters +
        '}';
  }
}

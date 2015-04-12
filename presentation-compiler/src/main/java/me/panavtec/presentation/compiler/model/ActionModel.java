package me.panavtec.presentation.compiler.model;

import javax.lang.model.type.TypeMirror;

public class ActionModel {
  private TypeMirror type;
  private String methodName;

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public TypeMirror getType() {
    return type;
  }

  public void setType(TypeMirror type) {
    this.type = type;
  }

  @Override public String toString() {
    return "ActionModel{" +
        "type=" + type +
        ", methodName='" + methodName + '\'' +
        '}';
  }
}

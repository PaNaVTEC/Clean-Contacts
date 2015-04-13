package me.panavtec.presentation.compiler.proxyviews.model;

import java.util.ArrayList;
import java.util.List;

public class EnclosingView {
  private String className;
  private String packageName;
  private List<ViewMethod> methods = new ArrayList<>();

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public List<ViewMethod> getMethods() {
    return methods;
  }

  public void setMethods(List<ViewMethod> methods) {
    this.methods = methods;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  @Override public String toString() {
    return "EnclosingView{" +
        "className='" + className + '\'' +
        ", packageName='" + packageName + '\'' +
        ", methods=" + methods +
        '}';
  }
}

package me.panavtec.presentation.compiler.outputs.model;

public class OutputModel {
  private String parentClassName;
  private String className;
  private String fieldName;
  private ActionModel onResult;
  private ActionModel onCancel;
  private ActionModel onError;

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public ActionModel getOnResult() {
    return onResult;
  }

  public void setOnResult(ActionModel onResult) {
    this.onResult = onResult;
  }

  public ActionModel getOnCancel() {
    return onCancel;
  }

  public void setOnCancel(ActionModel onCancel) {
    this.onCancel = onCancel;
  }

  public ActionModel getOnError() {
    return onError;
  }

  public void setOnError(ActionModel onError) {
    this.onError = onError;
  }

  public String getParentClassName() {
    return parentClassName;
  }

  public void setParentClassName(String parentClassName) {
    this.parentClassName = parentClassName;
  }

  @Override public String toString() {
    return "OutputModel{" +
        "parentClassName='" + parentClassName + '\'' +
        ", className='" + className + '\'' +
        ", fieldName='" + fieldName + '\'' +
        ", onResult=" + onResult +
        ", onCancel=" + onCancel +
        ", onError=" + onError +
        '}';
  }
}

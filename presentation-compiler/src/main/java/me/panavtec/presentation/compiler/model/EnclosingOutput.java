package me.panavtec.presentation.compiler.model;

import java.util.ArrayList;
import java.util.List;

public class EnclosingOutput {

  private String completeName;
  private String packageName;
  private String className;
  private List<OutputModel> outputs = new ArrayList<>();

  public String getCompleteName() {
    return completeName;
  }

  public void setCompleteName(String completeName) {
    this.completeName = completeName;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public List<OutputModel> getOutputs() {
    return outputs;
  }

  public void setOutputs(List<OutputModel> outputs) {
    this.outputs = outputs;
  }

  @Override public String toString() {
    return "EnclosingOutput{" +
        "completeName='" + completeName + '\'' +
        ", packageName='" + packageName + '\'' +
        ", className='" + className + '\'' +
        ", outputs=" + outputs +
        '}';
  }
}

package me.panavtec.presentation.compiler.outputs.writer;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import me.panavtec.presentation.common.Action;
import me.panavtec.presentation.common.DecoratedInteractorOutput;
import me.panavtec.presentation.compiler.outputs.model.ActionModel;
import me.panavtec.presentation.compiler.outputs.model.EnclosingOutput;
import me.panavtec.presentation.compiler.outputs.model.OutputModel;

public class OutputWriter {

  private static final String INNER_CLASS_SUFFIX = "_OutputInjector";
  private static final String TARGET = "target";

  public void write(Collection<EnclosingOutput> outputs, Filer filer) {
    for (EnclosingOutput output : outputs) {
      try {
        writeOutput(filer, output);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void writeOutput(Filer filer, EnclosingOutput parent) throws IOException {
    List<OutputModel> outputs = parent.getOutputs();
    MethodSpec.Builder methodBuilder = createMetod(parent);
    for (OutputModel output : outputs) {
      addNewCoordinatorStatement(methodBuilder, output);
    }
    TypeSpec coordinateInjectorClass = createInjectClass(parent, methodBuilder.build());
    JavaFile.builder(parent.getPackageName(), coordinateInjectorClass)
        .addFileComment("Do not modify this file!")
        .build()
        .writeTo(filer);
  }

  private void addNewCoordinatorStatement(MethodSpec.Builder methodBuilder, OutputModel output) {
    methodBuilder.addStatement(TARGET + ".$L = new $T<$T>().onResult($L).onError($L).onCancel($L).build()",
        output.getFieldName(), DecoratedInteractorOutput.Builder.class,
        ClassName.get(output.getOnResult().getType()), createAction(output.getOnResult()),
        createAction(output.getOnError()), createAction(output.getOnCancel()));
  }

  private TypeSpec createAction(ActionModel actionModel) {
    if (actionModel == null) {
      return null;
    }
    TypeName actionType = ClassName.get(actionModel.getType());
    TypeSpec.Builder builder = TypeSpec.anonymousClassBuilder("")
        .addSuperinterface(ParameterizedTypeName.get(ClassName.get(Action.class), actionType))
        .addMethod(MethodSpec.methodBuilder("onAction")
            .addParameter(ParameterSpec.builder(actionType, "data", Modifier.FINAL).build())
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PUBLIC)
            .addStatement(TARGET + ".$L(data)", actionModel.getMethodName())
            .build());
    return builder.build();
  }

  private MethodSpec.Builder createMetod(EnclosingOutput parent) {
    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("injectOutputs");
    methodBuilder.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .returns(void.class)
        .addParameter(ClassName.get(parent.getPackageName(), parent.getClassName()), TARGET,
            Modifier.FINAL);
    return methodBuilder;
  }

  private TypeSpec createInjectClass(EnclosingOutput parent, MethodSpec coordinateInject) {
    return TypeSpec.classBuilder(parent.getClassName() + INNER_CLASS_SUFFIX)
        .addModifiers(Modifier.PUBLIC)
        .addMethod(coordinateInject)
        .build();
  }
}

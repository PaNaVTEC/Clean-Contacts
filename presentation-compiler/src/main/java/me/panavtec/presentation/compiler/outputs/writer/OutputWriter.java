package me.panavtec.presentation.compiler.outputs.writer;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import me.panavtec.presentation.common.outputs.Action;
import me.panavtec.presentation.common.outputs.DecoratedInteractorOutput;
import me.panavtec.presentation.compiler.outputs.model.ActionModel;
import me.panavtec.presentation.compiler.outputs.model.EnclosingOutput;
import me.panavtec.presentation.compiler.outputs.model.OutputModel;
import me.panavtec.presentation.compiler.proxyviews.ViewAnnotationProcessor;

public class OutputWriter {

  private static final String INNER_CLASS_SUFFIX = "_OutputInjector";
  private static final String TARGET = "target";
  private static final String WEAK_PRESENTER = "weakPresenter";
  private static final String INJECT_METHOD = "injectOutputs";
  public static final String ON_ACTION = "onAction";
  public static final String ON_RESULT = "onResult";
  public static final String ON_ERROR = "onError";
  public static final String ON_CANCEL = "onCancel";

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
    MethodSpec.Builder methodBuilder = createInjectOutputs(parent);
    createFinalWeakReference(parent, methodBuilder);
    for (OutputModel output : outputs) {
      addNewCoordinatorStatement(parent, methodBuilder, output);
    }
    TypeSpec injectorClass = createInjectClass(parent, methodBuilder.build());
    JavaFile.builder(parent.getPackageName(), injectorClass)
        .addFileComment("Do not modify this file!")
        .build()
        .writeTo(filer);
  }

  private void createFinalWeakReference(EnclosingOutput parent, MethodSpec.Builder methodBuilder) {
    ClassName presenterClassName = ClassName.get(parent.getPackageName(), parent.getClassName());
    methodBuilder.addStatement("final $T<$T> $L = new $T<>($L)", WeakReference.class,
        presenterClassName, WEAK_PRESENTER, WeakReference.class, TARGET);
  }

  private void addNewCoordinatorStatement(EnclosingOutput parent, MethodSpec.Builder methodBuilder,
      OutputModel output) {
    methodBuilder.addStatement(
        "$L.$L = new $T<$T, $T>().\n" + "$L($L).\n" + "$L($L).\n" + "$L($L).\n" + "build()", TARGET,
        output.getFieldName(), DecoratedInteractorOutput.Builder.class,
        ClassName.get(output.getOnResult().getType()), ClassName.get(output.getOnError().getType()),
        ON_RESULT, createAction(parent, output.getOnResult()), ON_ERROR,
        createAction(parent, output.getOnError()), ON_CANCEL,
        createAction(parent, output.getOnCancel()));
  }

  private TypeSpec createAction(EnclosingOutput parent, ActionModel actionModel) {
    if (actionModel == null) {
      return null;
    }
    ClassName presenterClassName = ClassName.get(parent.getPackageName(), parent.getClassName());
    TypeName actionType = ClassName.get(actionModel.getType());
    String presenterLocalVar = "presenter";
    String parameterVar = "data";
    TypeSpec.Builder builder = TypeSpec.anonymousClassBuilder("")
        .addSuperinterface(ParameterizedTypeName.get(ClassName.get(Action.class), actionType))
        .addMethod(MethodSpec.methodBuilder(ON_ACTION)
            .addParameter(ParameterSpec.builder(actionType, parameterVar).build())
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PUBLIC)
            .addStatement("$T $L = $L.get()", presenterClassName, presenterLocalVar, WEAK_PRESENTER)
            .beginControlFlow("if ($L != null)", presenterLocalVar)
            .addStatement("$L.$L(data)", presenterLocalVar, actionModel.getMethodName())
            .endControlFlow()
            .build());
    return builder.build();
  }

  private MethodSpec.Builder createInjectOutputs(EnclosingOutput parent) {
    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(INJECT_METHOD);
    methodBuilder.addModifiers(Modifier.PUBLIC, Modifier.STATIC).
        returns(void.class).
        addParameter(ClassName.get(parent.getPackageName(), parent.getClassName()), TARGET);
    return methodBuilder;
  }

  private TypeSpec createInjectClass(EnclosingOutput parent, MethodSpec coordinateInject) {
    return TypeSpec.classBuilder(parent.getClassName() + INNER_CLASS_SUFFIX)
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(AnnotationSpec.builder(Generated.class)
            .addMember("value", "$S", ViewAnnotationProcessor.class.getCanonicalName())
            .build())
        .addMethod(coordinateInject)
        .build();
  }
}

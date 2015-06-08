package me.panavtec.presentation.compiler.proxyviews.writer;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import me.panavtec.presentation.common.ThreadSpec;
import me.panavtec.presentation.common.proguard.DoNotStrip;
import me.panavtec.presentation.compiler.proxyviews.ViewAnnotationProcessor;
import me.panavtec.presentation.compiler.proxyviews.model.EnclosingView;
import me.panavtec.presentation.compiler.proxyviews.model.ViewMethod;

public class ViewWriter {

  private static final String CLASS_PREFIX = "Decorated";
  private static final String VIEW_FIELD_NAME = "undecoratedView";
  private static final String THREADSPEC_FIELD_NAME = "threadSpec";

  public void write(Collection<EnclosingView> enclosingViews, Filer filer) {
    for (EnclosingView view : enclosingViews) {
      try {
        writeView(filer, view);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void writeView(Filer filer, EnclosingView view) throws IOException {
    List<MethodSpec> viewMethods = new ArrayList<>();
    List<ViewMethod> methods = view.getMethods();
    for (ViewMethod method : methods) {
      viewMethods.add(processMethod(method));
    }
    TypeSpec coordinateInjectorClass = createInjectClass(view, viewMethods);
    JavaFile.builder(view.getPackageName(), coordinateInjectorClass)
        .addFileComment("Do not modify this file!")
        .build()
        .writeTo(filer);
  }

  private MethodSpec processMethod(ViewMethod method) {
    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getMethodName())
        .addModifiers(Modifier.PUBLIC)
        .returns(ClassName.get(method.getReturnType()));

    List<TypeMirror> parameters = method.getParameters();
    String paramInvocation = "";
    for (int i = 0; i < parameters.size(); i++) {
      String paramName = "param" + i;
      methodBuilder.addParameter(ClassName.get(parameters.get(i)), paramName, Modifier.FINAL);
      paramInvocation += paramName;
      if (parameters.size() - 1 != i) {
        paramInvocation += ", ";
      }
    }

    if (method.isDecorate()) {
      methodBuilder.
          addAnnotation(Override.class).
          addStatement("this." + THREADSPEC_FIELD_NAME + ".execute($L)",
              createRunnable("$L.$L($L)", VIEW_FIELD_NAME, method.getMethodName(), paramInvocation));
    } else {
      methodBuilder.
          addAnnotation(Override.class).
          addStatement("$L.$L($L)", VIEW_FIELD_NAME, method.getMethodName(), paramInvocation);
    }

    return methodBuilder.build();
  }

  private TypeSpec createRunnable(String format, Object... parameters) {
    return TypeSpec.anonymousClassBuilder("")
        .addSuperinterface(ParameterizedTypeName.get(Runnable.class))
        .addMethod(MethodSpec.methodBuilder("run")
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PUBLIC)
            .addStatement(format, parameters)
            .build())
        .build();
  }

  private TypeSpec createInjectClass(EnclosingView view, List<MethodSpec> viewMethods) {
    ClassName viewType = ClassName.get(view.getPackageName(), view.getClassName());
    TypeSpec.Builder classBuilder = TypeSpec.classBuilder(CLASS_PREFIX + view.getClassName())
        .addModifiers(Modifier.PUBLIC)
        .addSuperinterface(viewType)
        .addAnnotation(AnnotationSpec.builder(DoNotStrip.class).build())
        .addAnnotation(AnnotationSpec.builder(Generated.class)
            .addMember("value", "$S", ViewAnnotationProcessor.class.getCanonicalName())
            .build());
    addDecoratedViewConsutrctor(classBuilder, viewType);
    addDecoratedViewFields(classBuilder, viewType);
    addDecoratedViewMethods(viewMethods, classBuilder);

    return classBuilder.build();
  }

  private void addDecoratedViewMethods(List<MethodSpec> viewMethods,
      TypeSpec.Builder classBuilder) {
    for (MethodSpec methodSpec : viewMethods) {
      classBuilder.addMethod(methodSpec);
    }
  }

  private void addDecoratedViewFields(TypeSpec.Builder classBuilder, ClassName viewType) {
    classBuilder.addField(viewType, VIEW_FIELD_NAME, Modifier.FINAL, Modifier.PRIVATE);
    classBuilder.addField(ClassName.get(ThreadSpec.class), THREADSPEC_FIELD_NAME, Modifier.FINAL,
        Modifier.PRIVATE);
  }

  private void addDecoratedViewConsutrctor(TypeSpec.Builder classBuilder, ClassName viewType) {
    classBuilder.addMethod(MethodSpec.constructorBuilder().
        addAnnotation(AnnotationSpec.builder(DoNotStrip.class).build()).
        addModifiers(Modifier.PUBLIC).
        addParameter(viewType, VIEW_FIELD_NAME).
        addParameter(ClassName.get(ThreadSpec.class), THREADSPEC_FIELD_NAME).
        addStatement("this." + VIEW_FIELD_NAME + " = " + VIEW_FIELD_NAME).
        addStatement("this." + THREADSPEC_FIELD_NAME + " = " + THREADSPEC_FIELD_NAME).
        build());
  }
}

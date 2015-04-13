package me.panavtec.presentation.compiler.outputs;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import me.panavtec.presentation.common.qualifiers.OnCancel;
import me.panavtec.presentation.common.qualifiers.OnError;
import me.panavtec.presentation.common.qualifiers.OnResult;
import me.panavtec.presentation.common.qualifiers.Output;
import me.panavtec.presentation.compiler.tools.ElementTools;
import me.panavtec.presentation.compiler.outputs.model.ActionModel;
import me.panavtec.presentation.compiler.outputs.model.EnclosingOutput;
import me.panavtec.presentation.compiler.outputs.model.OutputModel;
import me.panavtec.presentation.compiler.outputs.writer.OutputWriter;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class OutputAnnotationProcessor extends AbstractProcessor {

  private boolean firstProcessing;
  private ElementTools elementTools = new ElementTools();
  private OutputWriter writer = new OutputWriter();

  @Override public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    firstProcessing = true;
  }

  @Override public boolean process(Set<? extends TypeElement> annotations,
      RoundEnvironment roundEnv) {
    System.out.println("Starting Output Processor");
    if (!firstProcessing) {
      return false;
    }
    firstProcessing = false;
    Collection<EnclosingOutput> enclosingOutputs = processAnnotations(roundEnv);
    writer.write(enclosingOutputs, processingEnv.getFiler());
    return true;
  }

  private Collection<EnclosingOutput> processAnnotations(RoundEnvironment roundEnv) {
    Set<? extends Element> elementOutputs = roundEnv.getElementsAnnotatedWith(Output.class);
    Map<String, EnclosingOutput> outputs = new HashMap<>();

    for (Element e : elementOutputs) {
      OutputModel outputModel = processOutput(e);
      String parentClassName = outputModel.getParentClassName();
      if (!outputs.containsKey(parentClassName)) {
        outputs.put(parentClassName, createParent(e));
      }
      outputs.get(parentClassName).getOutputs().add(outputModel);
    }
    return outputs.values();
  }

  private EnclosingOutput createParent(Element e) {
    EnclosingOutput parent = new EnclosingOutput();
    parent.setCompleteName(elementTools.getElementParentCompleteClassName(e));
    parent.setPackageName(elementTools.getElementPackagename(e));
    parent.setClassName(elementTools.getElementParentClassName(e));

    return parent;
  }

  private OutputModel processOutput(Element e) {
    OutputModel outputModel = new OutputModel();
    if (elementTools.isField(e)) {
      System.out.println("Processing element : " + elementTools.getFieldName(e));
      System.out.println("Parent class name : " + elementTools.getElementParentClassName(e));
      outputModel.setParentClassName(elementTools.getElementParentClassName(e));
      outputModel.setFieldName(elementTools.getFieldName(e));
      List<? extends Element> enclosedElements = e.getEnclosingElement().getEnclosedElements();
      for (Element parentElement : enclosedElements) {
        processOnResult(outputModel, parentElement);
        processOnError(outputModel, parentElement);
        processOnCancel(outputModel, parentElement);
      }
    }
    return outputModel;
  }

  private void processOnCancel(OutputModel outputModel, Element parentElement) {
    ActionModel onResult = processAction(parentElement, OnCancel.class);
    if (onResult != null) {
      outputModel.setOnCancel(onResult);
    }
  }

  private void processOnResult(OutputModel outputModel, Element parentElement) {
    ActionModel onResult = processAction(parentElement, OnResult.class);
    if (onResult != null) {
      outputModel.setOnResult(onResult);
    }
  }

  private void processOnError(OutputModel outputModel, Element parentElement) {
    ActionModel onError = processAction(parentElement, OnError.class);
    if (onError != null) {
      outputModel.setOnError(onError);
    }
  }

  private <A extends Annotation> ActionModel processAction(Element element,
      Class<A> annotationClass) {
    A annotation = element.getAnnotation(annotationClass);
    if (annotation != null) {
      System.out.println("Element: " + element.getSimpleName());
      if (elementTools.isMethod(element)) {
        ActionModel actionModel = new ActionModel();
        actionModel.setMethodName(elementTools.getFieldName(element));
        actionModel.setType(element.asType());
        if (element.getKind() == ElementKind.METHOD) {
          actionModel.setType(((ExecutableElement) element).getParameters().get(0).asType());
        }

        System.out.println(actionModel);
        return actionModel;
      }
    }
    return null;
  }

  @Override public Set<String> getSupportedAnnotationTypes() {
    Set<String> supportTypes = new LinkedHashSet<>();
    supportTypes.add(OnResult.class.getCanonicalName());
    supportTypes.add(OnError.class.getCanonicalName());
    supportTypes.add(Output.class.getCanonicalName());
    return supportTypes;
  }
}

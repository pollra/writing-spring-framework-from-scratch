package wsffs.springframework.boot.web.servlet.handler.definition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import lombok.Getter;
import wsffs.springframework.boot.web.servlet.anno.arguments.PathVariable;
import wsffs.springframework.boot.web.servlet.controller.exception.DispatchServletMethodException;
import wsffs.springframework.boot.web.servlet.handler.MethodArgumentDefinition;

public class MethodDefinition {

  private final Method method;
  @Getter
  private final Collection<MethodArgumentDefinition> methodArgumentDefinitions;

  public MethodDefinition(Method method) {

    Objects.requireNonNull(method);

    this.method = method;
    this.methodArgumentDefinitions = new ArrayList<>();
    for (Parameter parameter : method.getParameters()) {
      PathVariable annotation = parameter.getAnnotation(PathVariable.class);
      String variableName = Objects.isNull(annotation)
          ? parameter.getName()
          : annotation.value();
      Class<?> type = parameter.getType();
      methodArgumentDefinitions.add(new MethodArgumentDefinition(variableName, type));
    }
  }

  public Object invoke(Object instance, Object... parameters) {
    try {
      return this.method.invoke(instance, parameters);
    } catch (IllegalAccessException e) {
      throw new DispatchServletMethodException(e);
    } catch (InvocationTargetException e) {
      throw new DispatchServletMethodException(e);
    }
  }
}

package wsffs.springframework.boot.web.servlet.handler.definition;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import wsffs.springframework.boot.web.servlet.classify.HandlerClassifier;
import wsffs.springframework.boot.web.servlet.handler.MethodArgumentDefinition;

public class HandlerMethodDefinition {

  @Getter
  private HandlerDefinition handlerDefinition;
  private final MethodDefinition methodDefinition;
  private Object instance;

  public HandlerMethodDefinition(
      Method handledMethod, Object instance, Set<HandlerClassifier> handlerClassifiers) {

    Objects.requireNonNull(instance);

    this.methodDefinition = new MethodDefinition(handledMethod);
    this.instance = instance;

    HandlerClassifier handlerClassifier = handlerClassifiers.stream()
        .filter(initHandlerClassifier -> initHandlerClassifier.isMatched(handledMethod))
        .findFirst()
        .orElseThrow();
    final String url = handlerClassifier.getUrl(handledMethod);
    final HttpMethod httpMethod = handlerClassifier.getHttpMethod(handledMethod);
    this.handlerDefinition = new HandlerDefinition(httpMethod, url);
  }

  public Object invoke(Object... parameters) {
    return this.methodDefinition.invoke(this.instance, parameters);
  }

  public Collection<MethodArgumentDefinition> getArgumentDefinitions() {
    return this.methodDefinition.getMethodArgumentDefinitions();
  }
}

package wsffs.springframework.boot.web.servlet.classify;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import wsffs.springframework.boot.web.servlet.handler.definition.HttpMethod;

public interface HandlerClassifier {

  HttpMethod getHttpMethod(Method method);
  String getUrl(Method method);

  Class<?> getMapping();

  default boolean isMatched(Method method) {
    Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
    for (Annotation declaredAnnotation : declaredAnnotations) {
      if(getMapping().isInstance(declaredAnnotation)) {
        return true;
      }
    }
    return false;
  }
}


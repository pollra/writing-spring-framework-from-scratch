package wsffs.springframework.boot.web.servlet.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wsffs.springframework.beans.BeanNameUtils;
import wsffs.springframework.beans.factory.BeanFactory;
import wsffs.springframework.boot.web.servlet.classify.DeleteMappingClassifier;
import wsffs.springframework.boot.web.servlet.classify.GetMethodClassifier;
import wsffs.springframework.boot.web.servlet.classify.HandlerClassifier;
import wsffs.springframework.boot.web.servlet.classify.PostMethodClassifier;
import wsffs.springframework.boot.web.servlet.classify.PutMappingClassifier;
import wsffs.springframework.boot.web.servlet.handler.definition.HandlerDefinition;
import wsffs.springframework.boot.web.servlet.handler.definition.HandlerMethodDefinition;

public class RequestProcessingFactory {

  private final Logger logger = LoggerFactory.getLogger(RequestProcessingFactory.class);

  // key 정보가 uri 가 되면 get/post 같은 중복될 수 있는 매핑의 경우
  // 처리하지 못한다
  private Map<HandlerDefinition, HandlerMethodDefinition> uriProcessingMethods = new HashMap<>();

  private Set<HandlerClassifier> handlerClassifiers;
  private final BeanFactory beanFactory;

  public RequestProcessingFactory(Set<Class<?>> controllerClasses, BeanFactory beanFactory) {
    logger.info("controllerClasses.size() = " + controllerClasses.size());
    this.beanFactory = beanFactory;
    this.handlerClassifiers = new HashSet<>();
    this.handlerClassifiers.add(new PostMethodClassifier());
    this.handlerClassifiers.add(new GetMethodClassifier());
    this.handlerClassifiers.add(new PutMappingClassifier());
    this.handlerClassifiers.add(new DeleteMappingClassifier());
    controllerClasses.forEach(this::setupHttpMethods);
  }

  private void setupHttpMethods(Class<?> scanTarget) {
    Method[] declaredMethods = scanTarget.getDeclaredMethods();
    for (Method declaredMethod : declaredMethods) {
      String beanName = BeanNameUtils.getName(scanTarget.getSimpleName());
      final Object beanInstance = beanFactory.getBean(beanName);
      HandlerMethodDefinition handlerMethodDefinition = new HandlerMethodDefinition(declaredMethod, beanInstance, handlerClassifiers);
      HandlerDefinition handlerDefinition = handlerMethodDefinition.getHandlerDefinition();
      this.uriProcessingMethods.put(handlerDefinition, handlerMethodDefinition);
    }
  }

  public HandlerMethodDefinition getProcessMethod(HandlerDefinition handlerDefinition) {
    for (Map.Entry<HandlerDefinition, HandlerMethodDefinition> entry : uriProcessingMethods.entrySet()) {
      if( entry.getKey().isMatched(handlerDefinition)) {
        return entry.getValue();
      }
    }
    throw new IllegalArgumentException();
  }
}

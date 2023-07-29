package wsffs.springframework.boot.web.servlet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.session.SessionHandler;
import wsffs.springframework.beans.BeanScanner;
import wsffs.springframework.beans.factory.BeanFactory;
import wsffs.springframework.boot.web.servlet.anno.present.RestController;
import wsffs.springframework.boot.web.servlet.handler.RequestProcessingFactory;
import wsffs.springframework.boot.web.servlet.handler.definition.HandlerDefinition;
import wsffs.springframework.boot.web.servlet.handler.definition.HandlerMethodDefinition;
import wsffs.springframework.boot.web.servlet.request.binder.PrimitiveArgumentBinder;
import wsffs.springframework.boot.web.servlet.request.binder.RequestArgumentBinder;
import wsffs.springframework.boot.web.servlet.request.extractor.RequestArgumentExtractors;

public class ServletFrontController extends SessionHandler {

  private BeanFactory beanFactory;
  private RequestProcessingFactory requestProcessingFactory;
  private final RequestArgumentExtractors requestArgumentExtractors;
  private final RequestArgumentBinder requestArgumentBinder;

  public ServletFrontController(BeanFactory beanFactory) {

    this.beanFactory = beanFactory;
    BeanScanner beanScanner = new BeanScanner(beanFactory.getScanRootPath());
    Set<Class<?>> controllerClasses = beanScanner.scan(RestController.class);
    this.requestProcessingFactory = new RequestProcessingFactory(controllerClasses, beanFactory);
    this.requestArgumentExtractors = new RequestArgumentExtractors();
    this.requestArgumentBinder = new PrimitiveArgumentBinder();
  }

  public void doDispatch(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    // request uri 를 기준으로 처리 instance 와 처리 method 를 찾는다
    String requestURI = request.getRequestURI();
    String httpMethod = request.getMethod();
    HandlerDefinition requestHandlerDefinition = new HandlerDefinition(httpMethod, requestURI);
    HandlerMethodDefinition processMethod = requestProcessingFactory.getProcessMethod(requestHandlerDefinition);
    ObjectMapper mapper = new ObjectMapper();
    Object[] arguments = null;
    String requestMethod = request.getMethod();
//        if(HttpMethod.GET.name().equals(requestMethod)) {
//
//            // uri 에 있는 값을 비교해서 method 추출
//
//            // 추출된 메서드를 실행
//        } else {
//            ServletInputStream inputStream = request.getInputStream();
//            byte[] bytes = inputStream.readAllBytes();
//            String requestBody = new String(bytes);
//            Class<?>[] parameterTypes = processMethod.getParameters();
//            arguments = new Object[parameterTypes.length - 1];
//
//            final int argumentIndex = 0;
//            for (Class<?> parameterType : parameterTypes) {
//                try {
//                    Class<?> requestModel = Class.forName(parameterType.getName());
//                    Object requestArgument = mapper.readValue(requestBody, requestModel);
//                    arguments[argumentIndex] = requestArgument;
//                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
    Map<String, Object> extractVariables = requestArgumentExtractors.extract(processMethod.getHandlerDefinition(), request);
    Object[] objects = requestArgumentBinder.bindArguments(processMethod.getArgumentDefinitions(), extractVariables);

    Object invokeResult = processMethod.invoke(arguments);
    response.getWriter().write(mapper.writeValueAsString(invokeResult));
    // viewResolver 에서 처리

    // 처리 결과 리턴
  }

  @Override
  public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    baseRequest.setHandled(true);
    doDispatch(target, baseRequest, request, response);
  }
}

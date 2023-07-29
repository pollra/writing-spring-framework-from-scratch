package wsffs.springframework.boot.web.servlet.classify;

import java.lang.reflect.Method;
import wsffs.springframework.boot.web.servlet.anno.mappings.PutMapping;
import wsffs.springframework.boot.web.servlet.handler.definition.HttpMethod;

public class PutMappingClassifier implements HandlerClassifier{
    @Override
    public HttpMethod getHttpMethod(Method method) {
        PutMapping annotation = method.getAnnotation(PutMapping.class);
        return annotation.method();
    }

    @Override
    public String getUrl(Method method) {
        PutMapping annotation = method.getAnnotation(PutMapping.class);
        return annotation.value();
    }

    @Override
    public Class<?> getMapping() {
        return PutMapping.class;
    }
}

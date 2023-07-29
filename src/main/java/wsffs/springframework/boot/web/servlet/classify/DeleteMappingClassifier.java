package wsffs.springframework.boot.web.servlet.classify;

import java.lang.reflect.Method;
import wsffs.springframework.boot.web.servlet.anno.mappings.DeleteMapping;
import wsffs.springframework.boot.web.servlet.handler.definition.HttpMethod;

public class DeleteMappingClassifier implements HandlerClassifier{
    @Override
    public HttpMethod getHttpMethod(Method method) {
        DeleteMapping annotation = method.getAnnotation(DeleteMapping.class);
        return annotation.method();
    }

    @Override
    public String getUrl(Method method) {
        DeleteMapping annotation = method.getAnnotation(DeleteMapping.class);
        return annotation.value();
    }

    @Override
    public Class<?> getMapping() {
        return DeleteMapping.class;
    }
}

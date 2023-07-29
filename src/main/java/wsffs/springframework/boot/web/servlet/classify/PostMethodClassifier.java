package wsffs.springframework.boot.web.servlet.classify;

import java.lang.reflect.Method;
import wsffs.springframework.boot.web.servlet.anno.mappings.PostMapping;
import wsffs.springframework.boot.web.servlet.handler.definition.HttpMethod;

public class PostMethodClassifier implements HandlerClassifier {
    @Override
    public HttpMethod getHttpMethod(Method method) {
        PostMapping annotation = method.getAnnotation(PostMapping.class);
        return annotation.method();
    }

    @Override
    public String getUrl(Method method) {
        PostMapping annotation = method.getAnnotation(PostMapping.class);
        return annotation.value();
    }

    @Override
    public Class<?> getMapping() {
        return PostMapping.class;
    }
}

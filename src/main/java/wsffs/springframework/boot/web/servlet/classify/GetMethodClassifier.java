package wsffs.springframework.boot.web.servlet.classify;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wsffs.springframework.boot.web.servlet.anno.mappings.GetMapping;
import wsffs.springframework.boot.web.servlet.handler.definition.HttpMethod;

public class GetMethodClassifier implements HandlerClassifier {

    @Override
    public HttpMethod getHttpMethod(Method method) {
        GetMapping annotation = method.getAnnotation(GetMapping.class);
        return annotation.method();
    }

    @Override
    public String getUrl(Method method) {
        GetMapping annotation = method.getAnnotation(GetMapping.class);
        return annotation.value();
    }

    @Override
    public Class<?> getMapping() {
        return GetMapping.class;
    }
}

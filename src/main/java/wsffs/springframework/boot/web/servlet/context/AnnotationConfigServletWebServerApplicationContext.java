package wsffs.springframework.boot.web.servlet.context;

import wsffs.springframework.beans.BeansException;
import wsffs.springframework.context.ApplicationContext;

public class AnnotationConfigServletWebServerApplicationContext implements ApplicationContext {

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }
}

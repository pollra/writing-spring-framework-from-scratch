package wsffs.springframework.beans;

import java.lang.reflect.Constructor;

public interface BeanDefinition {

    String getBeanName();
    Class<?> getOriginalClass();
    Constructor<?> getBeanConstructor();
    Class<?>[] getDependencies();
}
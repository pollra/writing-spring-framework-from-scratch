package wsffs.springframework.beans;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;

public class DefaultBeanDefinition implements BeanDefinition {

    private final Class<?> originalClass;
    private final Constructor<?> constructor;
    private final Class<?>[] dependencies;

    public DefaultBeanDefinition(Class<?> clazz) {
        this.originalClass = clazz;
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        this.constructor = Arrays.stream(declaredConstructors)
                .max(Comparator.comparingInt(Constructor::getParameterCount))
                .orElseThrow();
        this.dependencies = this.constructor.getParameterTypes();
    }

    @Override
    public String getBeanName() {
        String simpleName = originalClass.getSimpleName();
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }

    @Override
    public Class<?> getOriginalClass() {
        return this.originalClass;
    }

    @Override
    public Constructor<?> getBeanConstructor() {
        return this.constructor;
    }

    @Override
    public Class<?>[] getDependencies() {
        return this.dependencies;
    }

    @Override
    public String toString() {
        return "DefaultBeanDefinition{" +
                "originalClass=" + originalClass +
                ", constructor=" + constructor +
                ", dependencies=" + Arrays.toString(dependencies) +
                '}';
    }
}


package wsffs.springframework.beans;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class BeanScanner {

    private final Class beanScanStartPoint;

    public BeanScanner(Class beanScanStartPoint) {
        this.beanScanStartPoint = beanScanStartPoint;
    }

    public Set<Class<?>> scan(Class<? extends Annotation>[] annotations) {
        final Set<Class<?>> result = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            Set<Class<?>> scan = scan(annotation);
            result.addAll(scan);
        }
        return result;
    }

    public Set<Class<?>> scan(Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(this.beanScanStartPoint.getPackageName(), Scanners.TypesAnnotated);
        return reflections.getTypesAnnotatedWith(annotation);
    }
}

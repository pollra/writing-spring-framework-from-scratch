package wsffs.springframework.beans;

import java.lang.reflect.Constructor;

public class BeanUtils {

    public static Constructor<?> getCandidateConstructor(Class<?> clazz) {
        return clazz.getDeclaredConstructors()[0];
    }
}

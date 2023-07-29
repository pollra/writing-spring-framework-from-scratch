package wsffs.springframework.beans;

public class BeanNameUtils {

    public static String getName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        return getName(simpleName);
    }

    public static String getName(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}

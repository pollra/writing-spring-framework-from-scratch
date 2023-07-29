package wsffs.springframework.boot.web.servlet.anno.mappings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import wsffs.springframework.boot.web.servlet.handler.definition.HttpMethod;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value() default "";

    HttpMethod method() default HttpMethod.GET;
}

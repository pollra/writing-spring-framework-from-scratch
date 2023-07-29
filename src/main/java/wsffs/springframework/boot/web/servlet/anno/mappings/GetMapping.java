package wsffs.springframework.boot.web.servlet.anno.mappings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import wsffs.springframework.boot.web.servlet.handler.definition.HttpMethod;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method= HttpMethod.GET)
public @interface GetMapping {

    String value() default "/";

    HttpMethod method() default HttpMethod.GET;
}

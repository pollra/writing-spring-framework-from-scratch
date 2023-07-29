package wsffs.springframework.boot.web.servlet.anno.arguments;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface PathVariable {

  /**
   * 값의 이름을 지정합니다.
   * 이는 url 에 지정되어있는 값 이름과 동일해야 합니다.
   */
  String value() default "";
}

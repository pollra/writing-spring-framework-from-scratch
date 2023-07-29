package wsffs.springframework.boot.web.servlet.handler.definition;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import wsffs.springframework.boot.web.servlet.anno.arguments.PathVariable;
import wsffs.springframework.boot.web.servlet.handler.MethodArgumentDefinition;


@DisplayName("MethodDefinition 테스트")
class MethodDefinitionTest {

  @DisplayName("생성자 테스트")
  @Nested
  public class Constructor {

    @DisplayName("@PathVariable(\"name\") String name = 정상 생성")
    @Test
    public void nameIsName() {
      Method nameIsName = null;
      try {
        nameIsName = MethodTarget.class.getMethod("nameIsName", String.class);
      } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
      MethodDefinition methodDefinition = new MethodDefinition(nameIsName);

      Collection<MethodArgumentDefinition> methodArgumentDefinitions = methodDefinition.getMethodArgumentDefinitions();
      methodArgumentDefinitions.forEach(System.out::println);
    }

    @DisplayName("(@PathVariable(\"name\") String name, @PathVariable(\"test\") String test) = 정상 생성")
    @Test
    public void multiPathVariable() {
      Method multiPathVariable = null;
      try {
        multiPathVariable = MethodTarget.class.getMethod("multiPathVariable", String.class, String.class);
      } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
      MethodDefinition methodDefinition = new MethodDefinition(multiPathVariable);

      Collection<MethodArgumentDefinition> methodArgumentDefinitions = methodDefinition.getMethodArgumentDefinitions();
      methodArgumentDefinitions.forEach(System.out::println);
    }
  }

  public static class MethodTarget {

    public String nameIsName(@PathVariable("name") String name) {
      return "";
    }

    public String multiPathVariable(@PathVariable("name") String name, @PathVariable("test") String test) {
      return "";
    }
  }
}

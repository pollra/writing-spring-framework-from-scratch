package wsffs.springframework.boot.web.servlet.handler.definition;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("HandlerDefinition 구현 테스트")
class HandlerDefinitionTest {

  @Nested
  @DisplayName("비교 테스트")
  public class IsMatched {

    @Test
    @DisplayName("[GET:/test/{testId}] == [GET:/test/1000} -> true")
    public void ok01() {

      HandlerDefinition persist = new HandlerDefinition(HttpMethod.GET, "/test/{testId}");
      HandlerDefinition request = new HandlerDefinition(HttpMethod.GET, "/test/10000");

      Assertions.assertTrue(persist.isMatched(request));
    }

    @Test
    @DisplayName("[GET:/test/{testId}] == [GET:/test/test} -> true")
    public void ok02() {

      HandlerDefinition persist = new HandlerDefinition(HttpMethod.GET, "/test/{testId}");
      HandlerDefinition request = new HandlerDefinition(HttpMethod.GET, "/test/test");

      Assertions.assertTrue(persist.isMatched(request));
    }

    @Test
    @DisplayName("[POST:/test/{testId}] == [GET:/test/1000} -> false")
    public void fail01() {
      HandlerDefinition persist = new HandlerDefinition(HttpMethod.POST, "/test/{testId}");
      HandlerDefinition request = new HandlerDefinition(HttpMethod.GET, "/test/10000");

      Assertions.assertFalse(persist.isMatched(request));
    }

    @Test
    @DisplayName("[GET:/test/{testId}] == [GET:/test/1000/name} -> false")
    public void fail02() {
      HandlerDefinition persist = new HandlerDefinition(HttpMethod.GET, "/test/{testId}");
      HandlerDefinition request = new HandlerDefinition(HttpMethod.GET, "/test/1000/name");

      Assertions.assertFalse(persist.isMatched(request));
    }

    @Test
    @DisplayName("[GET:/test/{testId}/name] == [GET:/test/1000} -> false")
    public void fail03() {
      HandlerDefinition persist = new HandlerDefinition(HttpMethod.GET, "/test/{testId}/name");
      HandlerDefinition request = new HandlerDefinition(HttpMethod.GET, "/test/1000");

      Assertions.assertFalse(persist.isMatched(request));
    }
  }
}

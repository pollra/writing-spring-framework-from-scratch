package wsffs.springframework.boot.web.servlet.handler.definition;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("DefinitionUrl 테스트")
class DefinitionUrlTest {

  @DisplayName("생성 테스트")
  @Nested
  public class Create {

    @DisplayName("생성 시 path + query -> 성공")
    @Test
    public void ok01() {

      DefinitionUrl definitionUrl = new DefinitionUrl("/users/{userId}/profile?type=mobile");

      assertNotNull(definitionUrl);
    }

    @DisplayName("생성 시 path -> 성공")
    @Test
    public void ok02() {

      DefinitionUrl definitionUrl = new DefinitionUrl("/users/{userId}/profile");

      assertNotNull(definitionUrl);
    }

    @DisplayName("생성 시 pathVariable 문법 틀림 -> 에러")
    @Test
    public void fail01() {

      assertThrows(RuntimeException.class, () -> {
        new DefinitionUrl("/users/{userId/profile");
      });
    }
  }

  @DisplayName("비교 함수 테스트")
  @Nested
  public class IsMatched {

    @DisplayName("URL 경로가 다르면 실패")
    @Test
    public void notEquals() {
      DefinitionUrl processUrl = new DefinitionUrl("/users/{userId}");
      DefinitionUrl requestUrl = new DefinitionUrl("/users");

      Assertions.assertFalse(processUrl.isMatched(requestUrl));
    }

    @DisplayName("URL 경로가 같으면 성공")
    @Test
    public void equals() {

      DefinitionUrl processUrl = new DefinitionUrl("/users/{userId}");
      DefinitionUrl requestUrl = new DefinitionUrl("/users/1");

      Assertions.assertTrue(processUrl.isMatched(requestUrl));
    }
  }
}

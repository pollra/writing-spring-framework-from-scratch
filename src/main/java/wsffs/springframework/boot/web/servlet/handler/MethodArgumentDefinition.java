package wsffs.springframework.boot.web.servlet.handler;

import lombok.Getter;
import lombok.ToString;

@ToString
public class MethodArgumentDefinition {

  @Getter
  private final String name;

  @Getter
  private final Class<?> type;

  public MethodArgumentDefinition(String name, Class<?> type) {
    this.name = name;
    this.type = type;
  }
}
package wsffs.springframework.boot.web.servlet.handler.definition;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import wsffs.springframework.boot.web.servlet.handler.definition.exception.DefinitionException;

@Slf4j
@ToString
public class HandlerDefinition {

  private HttpMethod httpMethod;
  private DefinitionUrl url;
  private List<PathVariableDefinition> pathVariableDefinitions;

  public HandlerDefinition(HttpMethod httpMethod, String url) {
    Objects.requireNonNull(httpMethod);
    Objects.requireNonNull(url);
    this.httpMethod = httpMethod;

    pathAndQueryMapping(url);
  }

  public HandlerDefinition(String httpMethodIsString, String url) {
    this(HttpMethod.valueOf(httpMethodIsString), url);
  }

  public void pathAndQueryMapping(String url) {
    String[] pathAndQuery = url.split("\\?");
    this.url = new DefinitionUrl(pathAndQuery[0]);
  }

  public boolean isMatched(HandlerDefinition requestHandlerDefinition) {
    HttpMethod requestHttpMethod = requestHandlerDefinition.httpMethod;
    DefinitionUrl requestUrl = requestHandlerDefinition.url;
    log.info("persist: "+ this);
    log.info("request: "+requestHandlerDefinition);
    return this.url.isMatched(requestUrl)
        && this.httpMethod.equals(requestHttpMethod);
  }

  public Map<String, Object> getPathVariable(HandlerDefinition requestHandlerDefinition) {
    boolean isMatched = isMatched(requestHandlerDefinition);
    if(! isMatched) {
      throw new DefinitionException("해당 메서드로 처리할 수 없는 기능입니다.");
    }
    return url.getPathVariable(requestHandlerDefinition.url);
  }

  public Map<String, Object> getQueryVariable(HandlerDefinition requestHandlerDefinition) {
    boolean isNotMatched = ! isMatched(requestHandlerDefinition);
    if( isNotMatched ) {
      throw new DefinitionException("해당 메서드로 처리할 수 없는 기능입니다.");
    }
    return url.getQueryVariable(requestHandlerDefinition.url);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HandlerDefinition that = (HandlerDefinition) o;
    return httpMethod == that.httpMethod && Objects.equals(url, that.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(httpMethod, url);
  }
}

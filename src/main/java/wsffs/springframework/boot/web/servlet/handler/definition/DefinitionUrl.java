package wsffs.springframework.boot.web.servlet.handler.definition;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.eclipse.jetty.util.StringUtil;
import wsffs.springframework.boot.web.servlet.handler.definition.exception.DefinitionException;

public class DefinitionUrl {

  private final String path;
  private final String queries;

  public DefinitionUrl(String url) {
    String[] urlSplit = url.split("\\?");
    this.path = urlSplit[0];
    if(urlSplit.length >= 2) {
      this.queries = urlSplit[1];
    } else {
      this.queries = "";
    }

    // URL 매핑 구조화?

    // 어떤 형태의 값인지 어떻게 매핑?

    // 같음은 어떻게 증명?
  }

  // 밖의 주석

  private boolean urlHasPathVariable(String path) {
    if(path.contains("{")) {
      if(path.contains("}")) {
        return true;
      }
      throw new DefinitionException("올바르지 않은 URL 입니다.");
    }
    return false;
  }

  public boolean urlHasPathVariable() {
    return urlHasPathVariable(this.path);
  }

  public boolean uriHasQueryVariable() {
    return StringUtil.isNotBlank(queries);
  }

  public boolean isMatched(DefinitionUrl requestUrl) {
    String[] persistPaths = this.path.split("/");
    String[] requestPaths = requestUrl.path.split("/");

    if(persistPaths.length != requestPaths.length) {
      return false;
    }

    for (int i = 0; i < persistPaths.length; i++) {
      boolean isNotEquals = ! isMatched(persistPaths[i], requestPaths[i]);
      if(isNotEquals) {
        return false;
      }
    }
    return true;
  }

  private boolean isMatched(String persist, String request) {
    if(persist.contains("{")) {
      return true;
    }
    return persist.equals(request);
  }

  public Map<String, Object> getPathVariable(DefinitionUrl requestDefinition) {
    Map<String, Object> result = new HashMap<>();
    String[] persistPaths = this.path.split("/");
    String[] requestPaths = requestDefinition.path.split("/");

    for (int i = 0; i < persistPaths.length; i++) {
      if(persistPaths[i].contains("{")) {
        int startRange = persistPaths[i].indexOf("{");
        int endRange   = persistPaths[i].lastIndexOf("}");
        String argumentName = persistPaths[i].substring(startRange, endRange);
        result.put(argumentName, requestPaths[i]);
      }
    }
    return result;
  }

  public Map<String, Object> getQueryVariable(DefinitionUrl requestDefinition) {
    Map<String, Object> result = new HashMap<>();
    String[] queries = requestDefinition.queries.split("&");

    final int key = 0;
    final int value = 1;
    for (int i = 0; i < queries.length; i++) {
      String[] entry = queries[i].split("=");
      result.put(entry[key], entry[value]);
    }
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DefinitionUrl that = (DefinitionUrl) o;
    return Objects.equals(path, that.path) && Objects.equals(queries,
        that.queries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, queries);
  }
}

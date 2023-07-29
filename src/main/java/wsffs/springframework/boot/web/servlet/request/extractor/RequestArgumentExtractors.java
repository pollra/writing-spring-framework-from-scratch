package wsffs.springframework.boot.web.servlet.request.extractor;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import wsffs.springframework.boot.web.servlet.handler.definition.HandlerDefinition;
import wsffs.springframework.boot.web.servlet.request.extractor.PathVariableRequestArgumentExtractor;
import wsffs.springframework.boot.web.servlet.request.extractor.RequestArgumentExtractor;
import wsffs.springframework.boot.web.servlet.request.extractor.UrlQueryVariableRequestArgumentExtractor;

public class RequestArgumentExtractors {

  private final Collection<RequestArgumentExtractor> requestArgumentExtractors;

  public RequestArgumentExtractors() {

    this.requestArgumentExtractors = new ArrayList<>();
    this.requestArgumentExtractors.add(new UrlQueryVariableRequestArgumentExtractor());
    this.requestArgumentExtractors.add(new PathVariableRequestArgumentExtractor());
  }

  public Map<String, Object> extract(HandlerDefinition persistDefinition, HttpServletRequest request) {
    List<Map<String, Object>> extractResults = requestArgumentExtractors.stream()
        .filter(requestArgumentExtractor -> requestArgumentExtractor.isProcessed(persistDefinition, request))
        .map(requestArgumentExtractor -> requestArgumentExtractor.extract(persistDefinition, request))
        .collect(Collectors.toList());
    Map<String, Object> result = new HashMap<>();
    for (Map<String, Object> extractResult : extractResults) {
      result.putAll(extractResult);
    }
    return result;
  }
}

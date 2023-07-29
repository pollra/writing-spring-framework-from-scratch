package wsffs.springframework.boot.web.servlet.request.extractor;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import wsffs.springframework.boot.web.servlet.handler.definition.HandlerDefinition;

public interface RequestArgumentExtractor {

  Map<String, Object> extract(HandlerDefinition persistDefinition, HttpServletRequest request);
  boolean isProcessed(HandlerDefinition persistDefinition, HttpServletRequest request);
}

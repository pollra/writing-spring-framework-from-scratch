package wsffs.springframework.boot.web.servlet.request.binder;

import java.util.Collection;
import java.util.Map;
import wsffs.springframework.boot.web.servlet.handler.MethodArgumentDefinition;

public interface RequestArgumentBinder {

  Object[] bindArguments(Collection<MethodArgumentDefinition> argumentDefinitions, Map<String, Object> extractedVariables);
  boolean isProcessed(Map<String, Object> extractedVariables);
}

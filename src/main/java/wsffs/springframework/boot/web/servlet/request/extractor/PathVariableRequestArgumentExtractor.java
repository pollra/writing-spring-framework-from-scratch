package wsffs.springframework.boot.web.servlet.request.extractor;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import wsffs.springframework.boot.web.servlet.handler.definition.DefinitionUrl;
import wsffs.springframework.boot.web.servlet.handler.definition.HandlerDefinition;

public class PathVariableRequestArgumentExtractor implements RequestArgumentExtractor {

    @Override
    public Map<String, Object> extract(HandlerDefinition persistDefinition, HttpServletRequest request) {
        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        HandlerDefinition requestDefinition = new HandlerDefinition(requestMethod, requestURI);
        return persistDefinition.getPathVariable(requestDefinition);
    }

    @Override
    public boolean isProcessed(HandlerDefinition persistDefinition, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        DefinitionUrl requestDefinitionUrl = new DefinitionUrl(requestURI);
        return requestDefinitionUrl.urlHasPathVariable();
    }
}

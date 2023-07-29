package wsffs.springframework.boot.web.servlet.request.extractor;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import wsffs.springframework.boot.web.servlet.handler.definition.DefinitionUrl;
import wsffs.springframework.boot.web.servlet.handler.definition.HandlerDefinition;

public class UrlQueryVariableRequestArgumentExtractor implements RequestArgumentExtractor {

    @Override
    public Map<String, Object> extract(HandlerDefinition persistDefinition, HttpServletRequest request) {
        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        HandlerDefinition requestDefinition = new HandlerDefinition(requestMethod, requestURI);
        return requestDefinition.getQueryVariable(requestDefinition);
    }

    @Override
    public boolean isProcessed(HandlerDefinition persistDefinition, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        DefinitionUrl definitionUrl = new DefinitionUrl(requestURI);
        return definitionUrl.uriHasQueryVariable();
    }
}

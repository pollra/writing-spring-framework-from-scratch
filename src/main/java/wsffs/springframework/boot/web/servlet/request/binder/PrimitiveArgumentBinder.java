package wsffs.springframework.boot.web.servlet.request.binder;

import java.util.Collection;
import java.util.Map;
import wsffs.springframework.boot.web.servlet.handler.MethodArgumentDefinition;

public class PrimitiveArgumentBinder implements RequestArgumentBinder {

    @Override
    public Object[] bindArguments(Collection<MethodArgumentDefinition> argumentDefinitions, Map<String, Object> extractedVariables) {
        Object[] arguments = new Object[argumentDefinitions.size()];
        int argumentIndex = 0;
        for (MethodArgumentDefinition argumentDefinition : argumentDefinitions) {
            String name = argumentDefinition.getName();
            Object extractedVariable = extractedVariables.get(name);
            if(null != extractedVariable) {
                arguments[argumentIndex++] = extractedVariable;
            }
        }
        return arguments;
    }

    @Override
    public boolean isProcessed(Map<String, Object> extractedVariables) {
        boolean isNotProcessedType = false;
        for (Map.Entry<String, Object> extractedVariable : extractedVariables.entrySet()) {
            isNotProcessedType = ! isProcessedType(extractedVariable.getValue());
        }
        return isNotProcessedType;
    }

    public boolean isProcessedType(Object value) {
        if(value instanceof Integer  ) return true;
        if(value instanceof Long     ) return true;
        if(value instanceof Short    ) return true;
        if(value instanceof Float    ) return true;
        if(value instanceof Double   ) return true;
        if(value instanceof Boolean  ) return true;
        if(value instanceof Character) return true;
        if(value instanceof String   ) return true;
        return false;
    }
}

package masters.aggregatorservice.exception;

import java.util.Map;

public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "Search for entity %s not found for fields: %s";

    public NotFoundException(Class<?> targetEntity, Map<String, String> fields) {
        super(String.format(MESSAGE, targetEntity, fields));
    }

}

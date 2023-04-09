package masters.aggregatorservice.exception;

public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "Search for entity %s not found for name %s";

    public NotFoundException(Class<?> targetEntity, String tool) {
        super(String.format(MESSAGE, targetEntity, tool));
    }

}

package masters.staticanalysistools.aggregator.exception;

public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "Rule violation for tool %s not found for id %s";

    public NotFoundException(String ruleId, String tool) {
        super(String.format(MESSAGE, ruleId, tool));
    }

}

package masters.staticanalysistools.aggregator.exception.advice;

import lombok.RequiredArgsConstructor;
import masters.staticanalysistools.aggregator.exception.ParallelRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class AggregatorExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException(Exception exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(ParallelRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentNotValidException(ParallelRequestException exception) {

        return getErrorMessage(exception);
    }

    private Map<String, Object> getErrorMessage(Exception exception) {
        return Map.of("Error", "An error occurred", "Exception", exception);
    }

}

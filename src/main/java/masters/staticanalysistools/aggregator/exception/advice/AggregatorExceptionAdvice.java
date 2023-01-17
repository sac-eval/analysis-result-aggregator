package masters.staticanalysistools.aggregator.exception.advice;

import masters.staticanalysistools.aggregator.exception.NotFoundException;
import masters.staticanalysistools.aggregator.exception.ParallelRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class AggregatorExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException(Exception exception) {
        return getGenericError(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        return Map.of("Message", exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleNotFoundException(NotFoundException exception) {
        return Map.of("Message", exception.getMessage());
    }

    @ExceptionHandler(ParallelRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentNotValidException(ParallelRequestException exception) {
        return getGenericError(exception);
    }

    private Map<String, Object> getGenericError(Exception exception) {
        return Map.of("Error", "An error occurred", "Exception", exception.getMessage());
    }

}

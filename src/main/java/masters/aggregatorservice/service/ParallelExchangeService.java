package masters.aggregatorservice.service;

import masters.aggregatorservice.service.dto.TimedResult;
import org.springframework.core.ParameterizedTypeReference;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public interface ParallelExchangeService {

    <T, U> CompletableFuture<TimedResult<T>> postRequestAsync(URI uri, U body, ParameterizedTypeReference<T> parameterizedTypeReference);

}

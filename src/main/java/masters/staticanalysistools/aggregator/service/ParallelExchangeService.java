package masters.staticanalysistools.aggregator.service;

import org.springframework.core.ParameterizedTypeReference;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public interface ParallelExchangeService {

    <T, U> CompletableFuture<T> postRequestAsync(URI uri, U body,
        ParameterizedTypeReference<T> parameterizedTypeReference);

}

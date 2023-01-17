package masters.staticanalysistools.aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import masters.staticanalysistools.aggregator.exception.ParallelRequestException;
import masters.staticanalysistools.aggregator.service.ParallelExchangeService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Log
@Service
@RequiredArgsConstructor
public class ParallelExchangeServiceImpl implements ParallelExchangeService {

    private final RestTemplate restTemplate;

    @Override
    @Async
    public <T, U> CompletableFuture<T> postRequestAsync(URI uri, U body, ParameterizedTypeReference<T> parameterizedTypeReference) {
        try {
            long startTime = System.nanoTime();
            final HttpEntity<U> httpEntity = new HttpEntity<>(body);
            final ResponseEntity<T> result =
                restTemplate.exchange(uri, HttpMethod.POST, httpEntity, parameterizedTypeReference);
            long endTime = System.nanoTime();

            log.info(String.format("Execution time for %s took %d", uri, (endTime - startTime) / 1000000));

            return CompletableFuture.completedFuture(result.getBody());
        } catch (Exception exception) {
            return CompletableFuture.failedFuture(new ParallelRequestException(uri, exception));
        }
    }

}

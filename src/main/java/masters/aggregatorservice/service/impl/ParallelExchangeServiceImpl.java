package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import masters.aggregatorservice.exception.ParallelRequestException;
import masters.aggregatorservice.service.ParallelExchangeService;
import masters.aggregatorservice.service.dto.TimedResult;
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
    public <T, U> CompletableFuture<TimedResult<T>> postRequestAsync(URI uri, U body, ParameterizedTypeReference<T> parameterizedTypeReference) {
        try {
            long startTime = System.nanoTime();
            final HttpEntity<U> httpEntity = new HttpEntity<>(body);
            final ResponseEntity<T> result =
                restTemplate.exchange(uri, HttpMethod.POST, httpEntity, parameterizedTypeReference);
            long endTime = System.nanoTime();

            long time = (endTime - startTime);
            log.info(String.format("Execution time for %s took %d", uri, time / 1_000_000));

            return CompletableFuture.completedFuture(new TimedResult<>(time / 1_000_000_000.f, result.getBody()));
        } catch (Exception exception) {
            return CompletableFuture.failedFuture(new ParallelRequestException(uri, exception));
        }
    }

}

package masters.staticanalysistools.aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import masters.staticanalysistools.aggregator.exception.ParallelRequestException;
import masters.staticanalysistools.aggregator.service.ExchangeService;
import masters.staticanalysistools.aggregator.service.command.ParallelExchangeCommand;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final RestTemplate restTemplate;

    @Override
    public <T, U> List<T> requestMultipleInParallel(ParallelExchangeCommand<U> parallelExchangeCommand,
        ParameterizedTypeReference<T> parameterizedTypeReference) {
        final List<CompletableFuture<T>> completableFutureList = parallelExchangeCommand.getUrls().stream()
            .map(uri -> postRequestAsync(uri, parallelExchangeCommand.getBody(), parameterizedTypeReference)).toList();

        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));

        return completableFutureList.stream().map(CompletableFuture::join).toList();
    }

    @Async
    public <T, U> CompletableFuture<T> postRequestAsync(URI uri, U body,
        ParameterizedTypeReference<T> parameterizedTypeReference) {
        try {
            final HttpEntity<U> httpEntity = new HttpEntity<>(body);
            final ResponseEntity<T> result =
                restTemplate.exchange(uri, HttpMethod.POST, httpEntity, parameterizedTypeReference);

            return CompletableFuture.completedFuture(result.getBody());
        } catch (Exception exception) {
            return CompletableFuture.failedFuture(new ParallelRequestException(uri, exception));
        }
    }

}

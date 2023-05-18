package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.exception.ParallelRequestException;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.AverageLatencyService;
import masters.aggregatorservice.service.ParallelExchangeService;
import masters.aggregatorservice.service.SarifExchangeService;
import masters.aggregatorservice.service.command.SarifExchangeCommand;
import masters.aggregatorservice.service.dto.TimedResult;
import masters.aggregatorservice.service.dto.WrapperExchangeRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log
@Service
@RequiredArgsConstructor
public class SarifExchangeServiceImpl implements SarifExchangeService {

    private final ParallelExchangeService parallelExchangeService;

    private final AverageLatencyService averageLatencyService;

    @Override
    public List<Sarif> exchangeSarifList(SarifExchangeCommand command) {
        final WrapperExchangeRequest wrapperExchangeRequest = WrapperExchangeRequest.builder()
            .code(command.getCode())
            .encoded(command.isEncoded())
            .languageExtension(command.getLanguage().getExtension())
            .build();

        final Map<Tool, CompletableFuture<TimedResult<Sarif>>> completableFutureList = command.getTools().stream()
            .collect(Collectors.toMap(Function.identity(), tool -> parallelExchangeService.postRequestAsync(URI.create(tool.getUrl()), wrapperExchangeRequest,
                new ParameterizedTypeReference<>() {
                })
            ));

        CompletableFuture.allOf(completableFutureList.values().toArray(new CompletableFuture[0]));

        List<Sarif> sarifList = new ArrayList<>();

        for (Map.Entry<Tool, CompletableFuture<TimedResult<Sarif>>> completableFutureEntry : completableFutureList.entrySet()) {
            try {
                final Tool tool = completableFutureEntry.getKey();
                final TimedResult<Sarif> timedResult = completableFutureEntry.getValue().join();

                averageLatencyService.recordLatency(command.getLanguage(), tool, timedResult.getTime());
                sarifList.add(timedResult.getResult());
            } catch (CompletionException completionException) {
                log.warning(String.format("Execution for tool %s failed.", completableFutureEntry.getKey().getName()));

                if (!command.isToolFailureAllowed()) {
                    if (completionException.getCause() instanceof ParallelRequestException) {
                        throw (ParallelRequestException) completionException.getCause();
                    } else {
                        throw completionException;
                    }
                }
            }
        }

        return sarifList;
    }

}

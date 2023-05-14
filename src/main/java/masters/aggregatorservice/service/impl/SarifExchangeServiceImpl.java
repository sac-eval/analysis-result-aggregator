package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import masters.aggregatorservice.entity.Tool;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
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
            .language(command.getLanguage().getExtension())
            .build();

        final Map<Tool, CompletableFuture<TimedResult<Sarif>>> completableFutureList = command.getTools().stream()
            .collect(Collectors.toMap(Function.identity(), tool -> parallelExchangeService.postRequestAsync(URI.create(tool.getUrl()), wrapperExchangeRequest,
                new ParameterizedTypeReference<>() {
                })
            ));

        CompletableFuture.allOf(completableFutureList.values().toArray(new CompletableFuture[0]));

        return completableFutureList.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey,
                toolCompletableFutureEntry -> toolCompletableFutureEntry.getValue().join()))
            .entrySet().stream()
            .peek(toolCompletableFutureEntry ->
                averageLatencyService.recordLatency(command.getLanguage(),
                    toolCompletableFutureEntry.getKey(),
                    toolCompletableFutureEntry.getValue().getTime()))
            .map(Map.Entry::getValue)
            .map(TimedResult::getResult)
            .toList();
    }

}

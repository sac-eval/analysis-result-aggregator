package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;






import lombok.extern.java.Log;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.ParallelExchangeService;
import masters.aggregatorservice.service.SarifExchangeService;
import masters.aggregatorservice.service.command.SarifExchangeCommand;
import masters.aggregatorservice.service.dto.ExchangeServiceResponse;
import masters.aggregatorservice.service.dto.WrapperExchangeRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Log
@Service
@RequiredArgsConstructor
public class SarifExchangeServiceImpl implements SarifExchangeService {

    private final ParallelExchangeService parallelExchangeService;

    @Override
    public List<Sarif> exchangeSarifList(SarifExchangeCommand command) {
        final WrapperExchangeRequest wrapperExchangeRequest = WrapperExchangeRequest.builder()
            .code(command.getAnalysisCommand().getCode())
            .encoded(command.getAnalysisCommand().getEncoded())
            .language(command.getAnalysisCommand().getLanguage())
            .build();

        final List<CompletableFuture<ExchangeServiceResponse<Sarif>>> completableFutureList = command.getUrls().stream()
            .map(uri -> parallelExchangeService.postRequestAsync(uri, wrapperExchangeRequest,
                new ParameterizedTypeReference<ExchangeServiceResponse<Sarif>>() {
                })).toList();

        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));

        return completableFutureList.stream()
            .map(CompletableFuture::join)
            .map(ExchangeServiceResponse::getResult)
            .toList();
    }

}

package masters.staticanalysistools.aggregator.service;

import masters.staticanalysistools.aggregator.service.command.ParallelExchangeCommand;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public interface ExchangeService {

    <T, U> List<T> requestMultipleSarifAnalysisInParallel(ParallelExchangeCommand<U> parallelExchangeCommand,
        ParameterizedTypeReference<T> parameterizedTypeReference);

}

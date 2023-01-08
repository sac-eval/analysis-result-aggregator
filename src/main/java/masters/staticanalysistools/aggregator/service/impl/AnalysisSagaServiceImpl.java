package masters.staticanalysistools.aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import masters.staticanalysistools.aggregator.schema.Sarif;
import masters.staticanalysistools.aggregator.service.AggregationService;
import masters.staticanalysistools.aggregator.service.AnalysisSagaService;
import masters.staticanalysistools.aggregator.service.ExchangeService;
import masters.staticanalysistools.aggregator.service.ToolService;
import masters.staticanalysistools.aggregator.service.command.AggregationCommand;
import masters.staticanalysistools.aggregator.service.command.AnalysisCommand;
import masters.staticanalysistools.aggregator.service.command.ParallelExchangeCommand;
import masters.staticanalysistools.aggregator.service.dto.ExchangeServiceResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalysisSagaServiceImpl implements AnalysisSagaService {

    private final AggregationService aggregationService;

    private final ExchangeService exchangeService;

    private final ToolService toolService;

    @Override
    public Sarif analyse(AnalysisCommand analysisCommand) {
        final Map<String, URI> tools = toolService.getToolsForLanguage(analysisCommand.getLanguage());

        final ParallelExchangeCommand<AnalysisCommand> parallelExchangeCommand =
            ParallelExchangeCommand.<AnalysisCommand>builder().urls(tools.values()).body(analysisCommand).build();
        final List<ExchangeServiceResponse<Sarif>> exchangeServiceResponses =
            exchangeService.requestMultipleInParallel(parallelExchangeCommand,
                new ParameterizedTypeReference<>() {
                });

        final List<Sarif> sarifList =
            exchangeServiceResponses.stream().map(ExchangeServiceResponse::getResult).toList();

        final AggregationCommand aggregationCommand =
            AggregationCommand.builder()
                .sarifs(sarifList)
                .flattenViolations(analysisCommand.getFlattenViolations())
                .preferedTools(Collections.emptySet())
                .build();

        return aggregationService.aggregate(aggregationCommand);
    }

}

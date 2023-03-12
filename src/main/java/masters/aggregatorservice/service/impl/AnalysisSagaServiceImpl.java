package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.AggregationService;
import masters.aggregatorservice.service.AnalysisSagaService;
import masters.aggregatorservice.service.SarifExchangeService;
import masters.aggregatorservice.service.ToolService;
import masters.aggregatorservice.service.command.AggregationCommand;
import masters.aggregatorservice.service.command.AnalysisCommand;
import masters.aggregatorservice.service.command.SarifExchangeCommand;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalysisSagaServiceImpl implements AnalysisSagaService {

    private final AggregationService aggregationService;

    private final SarifExchangeService sarifExchangeService;

    private final ToolService toolService;

    @Override
    public Sarif analyse(AnalysisCommand analysisCommand) {
        final Map<String, URI> tools = toolService.getToolsForLanguage(analysisCommand.getLanguage());

        final SarifExchangeCommand sarifExchangeCommand =
            SarifExchangeCommand.builder().urls(tools.values()).analysisCommand(analysisCommand).build();
        final List<Sarif> sarifList = sarifExchangeService.exchangeSarifList(sarifExchangeCommand);

        final AggregationCommand aggregationCommand =
            AggregationCommand.builder()
                .sarifs(sarifList)
                .flattenViolations(analysisCommand.getFlattenViolations())
                .preferedTools(analysisCommand.getPreferedTools())
                .build();

        return aggregationService.aggregate(aggregationCommand);
    }

}

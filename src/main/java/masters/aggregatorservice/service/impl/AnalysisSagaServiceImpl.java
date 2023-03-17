package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.*;
import masters.aggregatorservice.service.command.AggregationCommand;
import masters.aggregatorservice.service.command.AnalysisCommand;
import masters.aggregatorservice.service.command.SarifExchangeCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnalysisSagaServiceImpl implements AnalysisSagaService {

    private final AggregationService aggregationService;

    private final SarifExchangeService sarifExchangeService;

    private final ToolQueryService toolQueryService;

    private final RuleViolationCommandService ruleViolationCommandService;

    private final StatisticCommandService statisticCommandService;

    @Override
    @Transactional
    public Sarif analyse(AnalysisCommand analysisCommand) {
        final Map<String, URI> tools = toolQueryService.getToolsForLanguage(analysisCommand.getLanguage());

        final List<Sarif> sarifList = getSarifs(analysisCommand, tools);

        final Set<RuleViolation> sarifRuleViolations = saveRuleViolationsFromSarifs(analysisCommand.getLanguage(), sarifList);

        statisticCommandService.updateOccurrences(sarifRuleViolations);

        return aggregateSarifs(analysisCommand, sarifList);
    }

    private List<Sarif> getSarifs(AnalysisCommand analysisCommand, Map<String, URI> tools) {
        final SarifExchangeCommand sarifExchangeCommand =
            SarifExchangeCommand.builder().urls(tools.values()).analysisCommand(analysisCommand).build();

        return sarifExchangeService.exchangeSarifList(sarifExchangeCommand);
    }

    private Set<RuleViolation> saveRuleViolationsFromSarifs(String language, List<Sarif> sarifList) {
        final Set<RuleViolation> sarifRuleViolations = new HashSet<>();
        sarifList.forEach(sarif -> {
            final Set<RuleViolation> ruleViolations = ruleViolationCommandService.createFromSarifForLanguage(sarif, language);
            sarifRuleViolations.addAll(ruleViolations);
        });

        return sarifRuleViolations;
    }

    private Sarif aggregateSarifs(AnalysisCommand analysisCommand, List<Sarif> sarifList) {
        final AggregationCommand aggregationCommand =
                AggregationCommand.builder()
                        .sarifs(sarifList)
                        .flattenViolations(analysisCommand.getFlattenViolations())
                        .preferedTools(analysisCommand.getPreferedTools())
                        .language(analysisCommand.getLanguage())
                        .build();

        return aggregationService.aggregate(aggregationCommand);
    }

}

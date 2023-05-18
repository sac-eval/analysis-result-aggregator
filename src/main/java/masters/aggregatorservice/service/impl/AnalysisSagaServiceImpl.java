package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.*;
import masters.aggregatorservice.service.command.AggregationCommand;
import masters.aggregatorservice.service.command.AnalysisCommand;
import masters.aggregatorservice.service.command.SarifExchangeCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnalysisSagaServiceImpl implements AnalysisSagaService {

    private final AggregationService aggregationService;

    private final SarifExchangeService sarifExchangeService;

    private final LanguageQueryService languageQueryService;

    private final ToolQueryService toolQueryService;

    private final RuleViolationCommandService ruleViolationCommandService;

    @Override
    @Transactional
    public Sarif analyse(AnalysisCommand analysisCommand) {
        final Language language = languageQueryService.findByExtension(analysisCommand.getLanguageExtension());
        final List<Tool> tools = toolQueryService.findByQuery(language, analysisCommand.getExcludedToolIds()).stream()
            .filter(Objects::nonNull)
            .toList();

        final List<Sarif> sarifList = getSarifs(analysisCommand, tools, language);
        sarifList.forEach(sarif -> ruleViolationCommandService.scheduleRuleViolationUpdateFromSarifForLanguage(sarif, language));

        return aggregateSarifs(analysisCommand, sarifList, language);
    }

    private List<Sarif> getSarifs(AnalysisCommand analysisCommand, List<Tool> tools, Language language) {
        final SarifExchangeCommand sarifExchangeCommand =
            SarifExchangeCommand.builder()
                .tools(tools)
                .code(analysisCommand.getCode())
                .encoded(analysisCommand.getEncoded())
                .language(language)
                .toolFailureAllowed(analysisCommand.isToolFailureAllowed())
                .build();

        return sarifExchangeService.exchangeSarifList(sarifExchangeCommand);
    }


    private Sarif aggregateSarifs(AnalysisCommand analysisCommand, List<Sarif> sarifList, Language language) {
        final AggregationCommand aggregationCommand =
            AggregationCommand.builder()
                .sarifs(sarifList)
                .customMessages(analysisCommand.isCustomMessages())
                .synonymInfo(analysisCommand.isSynonymInfo())
                .language(language)
                .build();

        return aggregationService.aggregate(aggregationCommand);
    }

}

package masters.staticanalysistools.aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import masters.staticanalysistools.aggregator.entity.RuleViolation;
import masters.staticanalysistools.aggregator.schema.ExternalProperties;
import masters.staticanalysistools.aggregator.schema.Result;
import masters.staticanalysistools.aggregator.schema.Run;
import masters.staticanalysistools.aggregator.schema.Sarif;
import masters.staticanalysistools.aggregator.service.AggregationService;
import masters.staticanalysistools.aggregator.service.RuleViolationQueryService;
import masters.staticanalysistools.aggregator.service.command.AggregationCommand;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AggregationServiceImpl implements AggregationService {

    private static final URI schema =
        URI.create("https://schemastore.azurewebsites.net/schemas/json/sarif-2.1.0-rtm.4.json");

    private final RuleViolationQueryService ruleViolationQueryService;

    @Override
    public Sarif aggregate(AggregationCommand aggregationCommand) {
        final List<Sarif> sarifs = aggregationCommand.getSarifs();

        final Sarif sarif = new Sarif();
        sarif.set$schema(schema);
        sarif.setVersion(Sarif.Version._2_1_0);

        final List<Run> aggregatedRuns =
            sarifs.stream()
                .flatMap(intermediate -> intermediate.getRuns().stream())
                .toList();
        final List<Run> finalizedRuns =
            aggregationCommand.getFlattenViolations() ? flattenSynonymViolations(aggregatedRuns) : aggregatedRuns;
        sarif.setRuns(finalizedRuns);

        final Set<ExternalProperties> aggregatedInlineExternalProperties =
            sarifs.stream()
                .flatMap(intermediate -> intermediate.getInlineExternalProperties().stream())
                .collect(Collectors.toSet());
        sarif.setInlineExternalProperties(aggregatedInlineExternalProperties);

        return sarif;
    }

    private List<Run> flattenSynonymViolations(List<Run> aggregatedRuns) {
        final Map<String, Set<String>> inclusionSetMap = new HashMap<>();

        // create a map of included result ids
        for (Run run : aggregatedRuns) {
            final String toolName = run.getTool().getDriver().getName();

            run.getResults().forEach(result -> {
                final Set<String> ruleIdSet = inclusionSetMap.getOrDefault(toolName, new HashSet<>());

                ruleIdSet.add(result.getRuleId());

                inclusionSetMap.put(toolName, ruleIdSet);
            });
        }

        // for each run and each result see if already included violations are synonyms, if they are remove them
        for (Run run : aggregatedRuns) {
            final String toolName = run.getTool().getDriver().getName();

            run.getResults().forEach(result -> {
                final RuleViolation ruleViolation =
                    ruleViolationQueryService.findRuleViolationByRuleIdAndTool(toolName, result.getRuleId());

                ruleViolation.getSynonyms()
                    .stream()
                    .filter(synonym -> inclusionSetMap.getOrDefault(synonym.getTool(), Collections.emptySet())
                        .contains(synonym.getRuleId()))
                    .forEach(synonym -> inclusionSetMap.get(synonym.getTool()).remove(synonym.getRuleId()));
            });
        }

        // include in final sarif only those rules that are left in inclusionSetMap
        for (Run run : aggregatedRuns) {
            final String toolName = run.getTool().getDriver().getName();

            final List<Result> includedResults = run.getResults().stream()
                .filter(result -> inclusionSetMap.get(toolName).contains(result.getRuleId()))
                .toList();

            run.setResults(includedResults);
        }

        return aggregatedRuns;
    }

}

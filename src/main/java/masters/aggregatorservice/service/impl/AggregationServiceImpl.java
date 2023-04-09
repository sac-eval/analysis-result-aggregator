package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.schema.ExternalProperties;
import masters.aggregatorservice.schema.PropertyBag;
import masters.aggregatorservice.schema.Run;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.AggregationService;
import masters.aggregatorservice.service.RuleViolationQueryService;
import masters.aggregatorservice.service.command.AggregationCommand;
import masters.aggregatorservice.service.dto.SynonymInfo;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;
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
        sarif.setRuns(aggregatedRuns);

        if (Boolean.TRUE.equals(aggregationCommand.getSynonymInfo())) {
            final Map<String, SynonymInfo> synonymInfoMap = retrieveSynonymInfo(aggregatedRuns, aggregationCommand.getLanguage());

            final PropertyBag propertyBag = new PropertyBag();
            synonymInfoMap.forEach(propertyBag::setAdditionalProperty);

            sarif.setProperties(propertyBag);
        }

        final Set<ExternalProperties> aggregatedInlineExternalProperties =
                sarifs.stream()
                        .flatMap(intermediate -> intermediate.getInlineExternalProperties().stream())
                        .collect(Collectors.toSet());
        sarif.setInlineExternalProperties(aggregatedInlineExternalProperties);


        return sarif;
    }

    private Map<String, SynonymInfo> retrieveSynonymInfo(List<Run> aggregatedRuns, String language) {
        final Map<String, SynonymInfo> synonymInfoMap = new HashMap<>();
        final Map<String, Set<String>> appearingRuleViolationsPerTool = new HashMap<>();

        // for each run and each result see if already included violations are synonyms, if they are remove them
        for (Run run : aggregatedRuns) {
            final String toolName = run.getTool().getDriver().getName();

            run.getResults().forEach(result -> {
                final Set<RuleViolation> synonyms =
                        ruleViolationQueryService.findSynonyms(result.getRuleId(), toolName, language);

                final List<RuleViolation> currentSynonyms = synonyms.stream()
                        .filter(synonym -> appearingRuleViolationsPerTool.getOrDefault(synonym.getTool().getName(), Collections.emptySet())
                                .contains(synonym.getRuleSarifId()))
                        .toList();

                currentSynonyms.forEach(ruleViolation -> {
                    final SynonymInfo synonymInfo = synonymInfoMap.getOrDefault(ruleViolation.getRuleSarifId(), new SynonymInfo(ruleViolation.getTool().getName()));

                    final Map<String, String> toolSynonymInfoMap = synonymInfo.getSynonyms();
                    toolSynonymInfoMap.put(toolName, result.getRuleId());

                    synonymInfoMap.put(ruleViolation.getRuleSarifId(), synonymInfo);
                });

                final Set<String> ruleIdSet = appearingRuleViolationsPerTool.getOrDefault(toolName, new HashSet<>());
                ruleIdSet.add(result.getRuleId());
                appearingRuleViolationsPerTool.put(toolName, ruleIdSet);
            });
        }

        return synonymInfoMap;
    }

}

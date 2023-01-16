package masters.staticanalysistools.aggregator.service;

import masters.staticanalysistools.aggregator.entity.RuleViolation;

import java.util.Set;

public interface RuleViolationQueryService {

    Set<RuleViolation> findSynonyms(String ruleId, String tool);

}

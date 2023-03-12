package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.RuleViolation;

import java.util.Set;

public interface RuleViolationQueryService {

    Set<RuleViolation> findSynonyms(String ruleId, String tool);

}

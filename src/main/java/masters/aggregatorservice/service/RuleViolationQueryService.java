package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.RuleViolation;

import java.util.Optional;
import java.util.Set;

public interface RuleViolationQueryService {

    Optional<RuleViolation> findRuleViolation(String ruleName, String tool, String language);

    Set<RuleViolation> findSynonyms(String ruleName, String tool, String language);

}

package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Tool;

import java.util.Optional;
import java.util.Set;

public interface RuleViolationQueryService {

    Optional<RuleViolation> findRuleViolation(String ruleName, String toolName, Language language);

    Optional<RuleViolation> findRuleViolation(String ruleName, Tool tool, Language language);

    Set<RuleViolation> findSynonyms(String ruleName, String tool, Language language);

}

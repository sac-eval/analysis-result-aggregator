package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.schema.Sarif;

import java.util.Set;

public interface RuleViolationCommandService {

    Set<RuleViolation> createRuleViolationsFromSarifForLanguage(Sarif sarif, Language language);

}

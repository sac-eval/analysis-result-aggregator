package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.schema.Sarif;

public interface RuleViolationCommandService {

    void scheduleRuleViolationUpdateFromSarifForLanguage(Sarif sarif, Language language);

    void updateScheduledRuleViolations(int processingSize);

}

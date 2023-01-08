package masters.staticanalysistools.aggregator.service;

import masters.staticanalysistools.aggregator.entity.RuleViolation;

public interface RuleViolationQueryService {

    RuleViolation findRuleViolationByRuleIdAndTool(String ruleId, String tool);

}

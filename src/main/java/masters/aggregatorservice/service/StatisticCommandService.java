package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.RuleViolation;

import java.util.Collection;

public interface StatisticCommandService {

    void updateOccurrences(Collection<RuleViolation> ruleViolations);

}

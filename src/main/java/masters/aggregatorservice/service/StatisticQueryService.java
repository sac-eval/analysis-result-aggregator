package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Statistic;

import java.util.Optional;

public interface StatisticQueryService {

    Optional<Statistic> findByRuleViolation(RuleViolation ruleViolation);

}

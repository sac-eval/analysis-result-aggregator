package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Statistic;
import masters.aggregatorservice.entity.Tool;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

public interface StatisticQueryService {

    Optional<Statistic> findByRuleViolation(RuleViolation ruleViolation);

}

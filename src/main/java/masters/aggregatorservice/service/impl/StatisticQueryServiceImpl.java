package masters.aggregatorservice.service.impl;

import lombok.AllArgsConstructor;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Statistic;
import masters.aggregatorservice.repository.StatisticRepository;
import masters.aggregatorservice.service.StatisticQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StatisticQueryServiceImpl implements StatisticQueryService {

    private final StatisticRepository statisticRepository;

    @Override
    public Optional<Statistic> findByRuleViolation(RuleViolation ruleViolation) {
        return statisticRepository.findByRuleViolation(ruleViolation);
    }

}

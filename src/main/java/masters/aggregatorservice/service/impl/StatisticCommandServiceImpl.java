package masters.aggregatorservice.service.impl;

import lombok.AllArgsConstructor;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Statistic;
import masters.aggregatorservice.repository.StatisticRepository;
import masters.aggregatorservice.service.StatisticCommandService;
import masters.aggregatorservice.service.StatisticQueryService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StatisticCommandServiceImpl implements StatisticCommandService {

    private final StatisticRepository statisticRepository;

    private final StatisticQueryService statisticQueryService;

    @Override
    public void updateOccurrences(Collection<RuleViolation> ruleViolations) {
        ruleViolations.forEach(ruleViolation -> {
            final Statistic statistic = statisticQueryService.findByRuleViolation(ruleViolation).orElseGet(() -> new Statistic(ruleViolation, 0L));

            statistic.setOccurrence(statistic.getOccurrence() + 1);

            statisticRepository.save(statistic);
        });
    }
}

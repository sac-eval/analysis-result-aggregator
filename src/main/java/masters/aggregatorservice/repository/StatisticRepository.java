package masters.aggregatorservice.repository;

import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    Optional<Statistic> findByRuleViolation(RuleViolation ruleViolation);

}

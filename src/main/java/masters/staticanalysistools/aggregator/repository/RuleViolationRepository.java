package masters.staticanalysistools.aggregator.repository;

import masters.staticanalysistools.aggregator.entity.RuleViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuleViolationRepository extends JpaRepository<RuleViolation, Long> {

    Optional<RuleViolation> findRuleViolationByRuleIdAndTool(String ruleId, String tool);

}

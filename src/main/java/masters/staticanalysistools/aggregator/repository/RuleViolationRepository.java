package masters.staticanalysistools.aggregator.repository;

import masters.staticanalysistools.aggregator.entity.RuleViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RuleViolationRepository extends JpaRepository<RuleViolation, Long> {

    @Query("select s from RuleViolation r join r.synonyms s where r.ruleId = :ruleId and r.tool = :tool")
    Set<RuleViolation> findAllSynonyms(String ruleId, String tool);

}

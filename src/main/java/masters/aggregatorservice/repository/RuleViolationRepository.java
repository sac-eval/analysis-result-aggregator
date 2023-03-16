package masters.aggregatorservice.repository;

import masters.aggregatorservice.entity.RuleViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RuleViolationRepository extends JpaRepository<RuleViolation, Long> {

    @Query(value = """
            SELECT rule_synonym.*
            FROM rule_violation rule 
                JOIN synonyms synonym_join ON rule.id=synonym_join.base_id
                JOIN rule_violation rule_synonym ON synonym_join.synonym_id = rule_synonym.id
            WHERE rule.id = :ruleId AND rule.tool = :tool
            UNION
            SELECT rule.*
            FROM rule_violation rule 
                JOIN synonyms synonym_join on rule.id=synonym_join.base_id 
                JOIN rule_violation rule_synonym ON synonym_join.synonym_id = rule_synonym.id
            WHERE rule_synonym.id = :ruleId AND rule_synonym.tool = :tool
            """,
            nativeQuery = true)
    Set<RuleViolation> findAllSynonyms(String ruleId, String tool);

}

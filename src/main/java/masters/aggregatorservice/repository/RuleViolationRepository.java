package masters.aggregatorservice.repository;

import masters.aggregatorservice.entity.RuleViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RuleViolationRepository extends JpaRepository<RuleViolation, Long> {

    Optional<RuleViolation> findByRuleSarifIdAndTool_NameAndLanguage_Name(String ruleName, String tool, String language);

    @Query(value = """
            SELECT rule_synonym.*
            FROM rule_violation rule
                JOIN tool t ON rule.tool_id = t.id
                JOIN language l ON rule.language_id = l.id
                JOIN synonyms synonym_join ON rule.id=synonym_join.base_id
                JOIN rule_violation rule_synonym ON synonym_join.synonym_id = rule_synonym.id
            WHERE rule.rule_sarif_id = :rule_name AND t.name = :tool AND l.name = :language
            UNION
            SELECT rule.*
            FROM rule_violation rule
                JOIN synonyms synonym_join on rule.id=synonym_join.base_id
                JOIN rule_violation rule_synonym ON synonym_join.synonym_id = rule_synonym.id
                JOIN tool t ON rule_synonym.tool_id = t.id
                JOIN language l ON rule_synonym.language_id = l.id
            WHERE rule_synonym.rule_sarif_id = :rule_name AND t.name = :tool AND l.name = :language
            """,
            nativeQuery = true)
    Set<RuleViolation> findAllSynonyms(@Param("rule_name") String ruleName, @Param("tool") String tool, @Param("language") String language);

}

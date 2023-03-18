package masters.aggregatorservice.repository;

import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    Optional<Tool> findByName(String name);

}

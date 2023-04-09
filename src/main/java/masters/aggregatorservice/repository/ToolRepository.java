package masters.aggregatorservice.repository;

import masters.aggregatorservice.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    Optional<Tool> findByName(String name);

    @Query(value = "SELECT t.* FROM tool t JOIN tool_language tl ON t.id = tl.tool_id JOIN language l ON tl.language_id = l.id WHERE l.name = :name",
            nativeQuery = true)
    List<Tool> findAllByLanguagesName(@Param("name") String name);

}

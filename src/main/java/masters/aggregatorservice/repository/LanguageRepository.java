package masters.aggregatorservice.repository;

import masters.aggregatorservice.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {


    @Query(value = "SELECT l.* FROM language l WHERE (:name is null or l.name LIKE '%:name%') and (:extension is null or l.extension LIKE '%:extension%')", nativeQuery = true)
    List<Language> findByQuery(String name, String extension);

    Optional<Language> findByExtension(String name);

}

package masters.aggregatorservice.repository;

import masters.aggregatorservice.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Optional<Language> findByName(String name);

    Optional<Language> findByExtension(String name);

}

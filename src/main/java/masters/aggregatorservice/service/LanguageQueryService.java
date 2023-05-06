package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;

import java.util.List;

public interface LanguageQueryService {

    List<Language> findByQuery(String name, String extension);

    Language findByExtension(String name);

    Language findById(Long id);

}

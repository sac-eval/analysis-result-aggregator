package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;

import java.util.List;

public interface LanguageQueryService {

    List<Language> findAll();

    Language findByExtension(String name);

}

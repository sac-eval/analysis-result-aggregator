package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;

public interface LanguageQueryService {

    Language findByName(String name);

}

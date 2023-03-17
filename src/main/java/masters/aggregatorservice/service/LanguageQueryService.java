package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.Tool;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

public interface LanguageQueryService {

    Optional<Language> findByName(String name);

}

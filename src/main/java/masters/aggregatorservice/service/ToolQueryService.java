package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.Tool;

import java.util.List;
import java.util.Set;

public interface ToolQueryService {

    List<Tool> findAll();

    List<Tool> findByQuery(Language language, Set<Long> excludedToolIds);

    List<Tool> findAllByLanguageName(String languageName);

    Tool findByName(String toolName);

}

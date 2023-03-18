package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Tool;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

public interface ToolQueryService {

    Optional<Tool> findByName(String name);

    Map<String, URI> getToolsForLanguage(String language);

}

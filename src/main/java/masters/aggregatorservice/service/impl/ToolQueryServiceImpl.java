package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.properties.AggregatorProperties;
import masters.aggregatorservice.repository.ToolRepository;
import masters.aggregatorservice.service.ToolQueryService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToolQueryServiceImpl implements ToolQueryService {

    private final AggregatorProperties aggregatorProperties;

    private final ToolRepository toolRepository;

    @Override
    public Optional<Tool> findByName(String name) {
        return toolRepository.findByName(name);
    }

    @Override
    public Map<String, URI> getToolsForLanguage(String language) {
        return aggregatorProperties.getTools().getOrDefault(language, Collections.emptyMap());
    }

}

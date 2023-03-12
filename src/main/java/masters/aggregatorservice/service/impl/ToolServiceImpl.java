package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.properties.AggregatorProperties;
import masters.aggregatorservice.service.ToolService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ToolServiceImpl implements ToolService {

    private final AggregatorProperties aggregatorProperties;

    @Override
    public Map<String, URI> getToolsForLanguage(String language) {
        return aggregatorProperties.getTools().getOrDefault(language, Collections.emptyMap());
    }

}

package masters.staticanalysistools.aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import masters.staticanalysistools.aggregator.properties.AggregatorProperties;
import masters.staticanalysistools.aggregator.service.ToolService;
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

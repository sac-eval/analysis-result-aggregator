package masters.staticanalysistools.aggregator.service;

import java.net.URI;
import java.util.Map;

public interface ToolService {

    Map<String, URI> getToolsForLanguage(String language);

}

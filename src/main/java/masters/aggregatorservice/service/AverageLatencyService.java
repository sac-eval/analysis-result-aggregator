package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.Tool;

import java.util.Optional;

public interface AverageLatencyService {

    void recordLatency(Language language, Tool tool, double latency);

    Optional<Double> calculateLatency(String languageName, String toolName);

}

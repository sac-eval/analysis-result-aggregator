package masters.staticanalysistools.aggregator.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "aggregation")
@Data
public class AggregatorProperties {

    private Map<String, Map<String, URI>> tools;

    private Integer maximumTimeout;

}

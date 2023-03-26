package masters.aggregatorservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aggregation")
@Data
public class AggregatorProperties {

    private Integer maximumTimeout;

}

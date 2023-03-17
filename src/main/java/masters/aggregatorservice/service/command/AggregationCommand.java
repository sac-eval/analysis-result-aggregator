package masters.aggregatorservice.service.command;

import lombok.Builder;
import lombok.Data;
import masters.aggregatorservice.schema.Sarif;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class AggregationCommand {

    private List<Sarif> sarifs;

    private Boolean flattenViolations;

    private Set<String> preferedTools;

    private String language;

}

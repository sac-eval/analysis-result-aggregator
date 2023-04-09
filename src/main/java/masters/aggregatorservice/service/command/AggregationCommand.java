package masters.aggregatorservice.service.command;

import lombok.Builder;
import lombok.Data;
import masters.aggregatorservice.schema.Sarif;

import java.util.List;

@Data
@Builder
public class AggregationCommand {

    private List<Sarif> sarifs;

    private Boolean synonymInfo;

    private String language;

}

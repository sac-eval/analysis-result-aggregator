package masters.aggregatorservice.service.command;

import lombok.Builder;
import lombok.Data;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.schema.Sarif;

import java.util.List;

@Data
@Builder
public class AggregationCommand {

    private List<Sarif> sarifs;

    private Boolean customMessages;

    private Boolean synonymInfo;

    private Language language;

}

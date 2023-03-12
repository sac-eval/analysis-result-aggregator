package masters.aggregatorservice.service;

import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.command.AggregationCommand;

public interface AggregationService {

    Sarif aggregate(AggregationCommand aggregationCommand);

}

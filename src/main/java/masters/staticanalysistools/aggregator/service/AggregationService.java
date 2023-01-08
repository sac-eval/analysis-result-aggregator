package masters.staticanalysistools.aggregator.service;

import masters.staticanalysistools.aggregator.schema.Sarif;
import masters.staticanalysistools.aggregator.service.command.AggregationCommand;

public interface AggregationService {

    Sarif aggregate(AggregationCommand aggregationCommand);

}

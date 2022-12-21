package masters.staticanalysistools.aggregator.service;

import masters.staticanalysistools.aggregator.schema.Sarif;

import java.util.List;

public interface AggregationService {

    Sarif aggregate(List<Sarif> sarifList);

}

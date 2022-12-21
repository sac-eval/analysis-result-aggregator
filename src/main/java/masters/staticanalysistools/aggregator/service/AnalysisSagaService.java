package masters.staticanalysistools.aggregator.service;

import masters.staticanalysistools.aggregator.schema.Sarif;
import masters.staticanalysistools.aggregator.service.command.AnalysisCommand;

public interface AnalysisSagaService {

    Sarif analyse(AnalysisCommand analysisCommand);

}

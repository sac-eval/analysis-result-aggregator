package masters.aggregatorservice.service;

import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.command.AnalysisCommand;

public interface AnalysisSagaService {

    Sarif analyse(AnalysisCommand analysisCommand);

}

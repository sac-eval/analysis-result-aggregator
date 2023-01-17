package masters.staticanalysistools.aggregator.service;

import masters.staticanalysistools.aggregator.schema.Sarif;
import masters.staticanalysistools.aggregator.service.command.SarifExchangeCommand;

import java.util.List;

public interface SarifExchangeService {

    List<Sarif> exchangeSarifList(SarifExchangeCommand sarifExchangeCommand);

}

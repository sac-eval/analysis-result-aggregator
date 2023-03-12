package masters.aggregatorservice.service;

import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.command.SarifExchangeCommand;

import java.util.List;

public interface SarifExchangeService {

    List<Sarif> exchangeSarifList(SarifExchangeCommand sarifExchangeCommand);

}

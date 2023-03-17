package masters.aggregatorservice.service.command;

import lombok.Data;

@Data
public class CreateRuleViolation {

    private String ruleSarifId;

    private String language;

    private String tool;

}

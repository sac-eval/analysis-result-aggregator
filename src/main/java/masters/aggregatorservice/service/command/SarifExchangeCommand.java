package masters.aggregatorservice.service.command;

import lombok.Builder;
import lombok.Data;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.Tool;

import java.util.List;

@Data
@Builder
public class SarifExchangeCommand {

    private List<Tool> tools;

    private String code;

    private boolean encoded;

    private Language language;

    private boolean toolFailureAllowed;

}

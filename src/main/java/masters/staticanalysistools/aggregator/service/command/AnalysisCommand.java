package masters.staticanalysistools.aggregator.service.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnalysisCommand {

    @NotBlank
    private String code;

    @NotNull
    private Boolean encoded;

    @NotBlank
    private String language;

    @NotNull
    private Boolean flattenViolations;

}

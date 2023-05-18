package masters.aggregatorservice.service.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AnalysisCommand {

    @NotBlank
    private String code;

    @NotNull
    private Boolean encoded;

    @NotNull
    private String languageExtension;

    private boolean synonymInfo;

    private boolean customMessages;

    private Set<Long> excludedToolIds = new HashSet<>();

    private boolean toolFailureAllowed = true;

}

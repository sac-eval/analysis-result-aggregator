package masters.aggregatorservice.controller.response;

import lombok.Data;

import java.util.Set;

@Data
public class LanguageResponse {
    private Long id;

    private String name;

    private String extension;

    private Set<ToolResponse> tools;
}

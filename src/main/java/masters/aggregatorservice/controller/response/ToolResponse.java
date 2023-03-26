package masters.aggregatorservice.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class ToolResponse {
    private Long id;

    private String name;

    private List<LanguageResponse> languages;
}

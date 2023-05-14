package masters.aggregatorservice.controller.response;

import lombok.Data;

@Data
public class ToolResponse {
    private Long id;

    private String name;

    private Double scanTime;
}

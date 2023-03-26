package masters.aggregatorservice.service.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SynonymInfo {
    private String tool;
    private Map<String, String> synonyms;

    public SynonymInfo(String tool) {
        this.tool = tool;

        synonyms = new HashMap<>();
    }
}

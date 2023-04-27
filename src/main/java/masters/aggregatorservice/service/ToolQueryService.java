package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Tool;

import java.util.List;

public interface ToolQueryService {

    List<Tool> findAll();

    List<Tool> findAllByLanguageName(String languageName);

    Tool findByName(String toolName);

}

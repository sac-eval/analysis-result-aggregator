package masters.aggregatorservice.service;

import masters.aggregatorservice.entity.Tool;

import java.util.List;

public interface ToolQueryService {

    List<Tool> findAll();

    List<Tool> findAllByLanguage(String language);

    Tool findByName(String name);

}

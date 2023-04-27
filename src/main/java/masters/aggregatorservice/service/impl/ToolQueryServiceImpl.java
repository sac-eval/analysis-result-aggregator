package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.exception.NotFoundException;
import masters.aggregatorservice.repository.ToolRepository;
import masters.aggregatorservice.service.ToolQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ToolQueryServiceImpl implements ToolQueryService {

    private final ToolRepository toolRepository;

    @Override
    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    @Override
    public List<Tool> findAllByLanguageName(String languageName) {
        return toolRepository.findAllByLanguagesName(languageName);
    }

    @Override
    public Tool findByName(String toolName) {
        return toolRepository.findByName(toolName).orElseThrow(() -> new NotFoundException(Tool.class, Map.of("name", toolName)));
    }

}

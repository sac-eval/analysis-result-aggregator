package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.exception.NotFoundException;
import masters.aggregatorservice.repository.ToolRepository;
import masters.aggregatorservice.service.ToolQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolQueryServiceImpl implements ToolQueryService {

    private final ToolRepository toolRepository;

    @Override
    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    @Override
    public List<Tool> findAllByLanguage(String language) {
        return toolRepository.findAllByLanguagesName(language);
    }

    @Override
    public Tool findByName(String name) {
        return toolRepository.findByName(name).orElseThrow(() -> new NotFoundException(Tool.class, name));
    }

}

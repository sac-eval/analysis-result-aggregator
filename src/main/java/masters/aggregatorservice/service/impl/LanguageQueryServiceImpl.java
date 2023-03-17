package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.properties.AggregatorProperties;
import masters.aggregatorservice.repository.LanguageRepository;
import masters.aggregatorservice.service.LanguageQueryService;
import masters.aggregatorservice.service.ToolQueryService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageQueryServiceImpl implements LanguageQueryService {

    private final LanguageRepository languageRepository;

    @Override
    public Optional<Language> findByName(String name) {
        return languageRepository.findByName(name);
    }

}

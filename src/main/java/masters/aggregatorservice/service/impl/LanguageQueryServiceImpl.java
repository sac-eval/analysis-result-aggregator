package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.exception.NotFoundException;
import masters.aggregatorservice.repository.LanguageRepository;
import masters.aggregatorservice.service.LanguageQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LanguageQueryServiceImpl implements LanguageQueryService {

    private final LanguageRepository languageRepository;

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public List<Language> findByQuery(String name, String extension) {
        return languageRepository.findByQuery(name, extension);
    }

    @Override
    public Language findByExtension(String extension) {
        return languageRepository.findByExtension(extension).orElseThrow(() -> new NotFoundException(Language.class, Map.of("extension", extension)));
    }

    @Override
    public Language findById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> new NotFoundException(Language.class, Map.of("id", id.toString())));
    }

}

package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.exception.NotFoundException;
import masters.aggregatorservice.repository.LanguageRepository;
import masters.aggregatorservice.service.LanguageQueryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageQueryServiceImpl implements LanguageQueryService {

    private final LanguageRepository languageRepository;

    @Override
    public Language findByName(String name) {
        return languageRepository.findByName(name).orElseThrow(() -> new NotFoundException(Language.class, name));
    }

}

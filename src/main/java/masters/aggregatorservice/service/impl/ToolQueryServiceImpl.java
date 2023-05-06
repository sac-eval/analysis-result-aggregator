package masters.aggregatorservice.service.impl;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.Language_;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.entity.Tool_;
import masters.aggregatorservice.exception.NotFoundException;
import masters.aggregatorservice.repository.ToolRepository;
import masters.aggregatorservice.service.ToolQueryService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ToolQueryServiceImpl implements ToolQueryService {

    private final ToolRepository toolRepository;

    @Override
    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    @Override
    public List<Tool> findByQuery(Language language, Set<Long> excludedToolIds) {
        final Specification<Tool> toolSpecification = ((root, query, criteriaBuilder) -> {
            final List<Predicate> predicateList = new ArrayList<>();

            Join<Tool, Language> join = root.join(Tool_.LANGUAGES);
            predicateList.add(criteriaBuilder.equal(join.get(Language_.ID), language.getId()));
            if (!excludedToolIds.isEmpty()) {
                predicateList.add(criteriaBuilder.not(root.get(Tool_.ID).in(excludedToolIds)));
            }

            return criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
        });

        return toolRepository.findAll(toolSpecification);
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

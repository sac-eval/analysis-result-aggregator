package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.repository.RuleViolationRepository;
import masters.aggregatorservice.service.RuleViolationQueryService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RuleViolationQueryServiceImpl implements RuleViolationQueryService {

    private final RuleViolationRepository ruleViolationRepository;

    @Override
    public Set<RuleViolation> findSynonyms(String ruleId, String tool) {
        return ruleViolationRepository.findAllSynonyms(ruleId, tool);
    }

}

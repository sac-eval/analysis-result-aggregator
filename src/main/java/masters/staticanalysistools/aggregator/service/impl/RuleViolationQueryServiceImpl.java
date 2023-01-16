package masters.staticanalysistools.aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import masters.staticanalysistools.aggregator.entity.RuleViolation;
import masters.staticanalysistools.aggregator.repository.RuleViolationRepository;
import masters.staticanalysistools.aggregator.service.RuleViolationQueryService;
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

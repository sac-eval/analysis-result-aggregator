package masters.staticanalysistools.aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import masters.staticanalysistools.aggregator.entity.RuleViolation;
import masters.staticanalysistools.aggregator.exception.NotFoundException;
import masters.staticanalysistools.aggregator.repository.RuleViolationRepository;
import masters.staticanalysistools.aggregator.service.RuleViolationQueryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleViolationQueryServiceImpl implements RuleViolationQueryService {

    private final RuleViolationRepository ruleViolationRepository;

    @Override
    public RuleViolation findRuleViolationByRuleIdAndTool(String ruleId, String tool) {
        return ruleViolationRepository
            .findRuleViolationByRuleIdAndTool(ruleId, tool).orElseThrow(() -> new NotFoundException(ruleId, tool));
    }

}

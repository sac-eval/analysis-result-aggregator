package masters.staticanalysistools.aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import masters.staticanalysistools.aggregator.entity.RuleViolation;
import masters.staticanalysistools.aggregator.repository.RuleViolationRepository;
import masters.staticanalysistools.aggregator.service.RuleViolationQueryService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RuleViolationQueryServiceImpl implements RuleViolationQueryService {

    private final RuleViolationRepository ruleViolationRepository;

    @Override
    public RuleViolation findRuleViolationByRuleIdAndTool(String ruleId, String tool) {
        final RuleViolation defaultRuleViolation = new RuleViolation();
        defaultRuleViolation.setRuleId(ruleId);
        defaultRuleViolation.setTool(tool);
        defaultRuleViolation.setSynonyms(Collections.emptySet());

        return ruleViolationRepository
            .findRuleViolationByRuleIdAndTool(ruleId, tool).orElse(defaultRuleViolation);
    }

}

package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.repository.RuleViolationRepository;
import masters.aggregatorservice.service.RuleViolationQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RuleViolationQueryServiceImpl implements RuleViolationQueryService {

    private final RuleViolationRepository ruleViolationRepository;

    @Override
    public Optional<RuleViolation> findRuleViolation(String ruleName, String tool, String language) {
        return ruleViolationRepository.findByRuleSarifIdAndTool_NameAndLanguage_Name(ruleName, tool, language);
    }

    @Override
    public Set<RuleViolation> findSynonyms(String ruleName, String tool, String language) {
        return ruleViolationRepository.findAllSynonyms(ruleName, tool, language);
    }

}

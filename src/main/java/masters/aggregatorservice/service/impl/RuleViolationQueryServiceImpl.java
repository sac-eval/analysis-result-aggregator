package masters.aggregatorservice.service.impl;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Tool;
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
    public Optional<RuleViolation> findRuleViolation(String ruleName, Tool tool, Language language) {
        return ruleViolationRepository.findByRuleSarifIdAndToolAndLanguage(ruleName, tool, language);
    }

    @Override
    public Set<RuleViolation> findSynonyms(String ruleName, String tool, Language language) {
        return ruleViolationRepository.findAllSynonyms(ruleName, tool, language.getId());
    }

}

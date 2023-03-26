package masters.aggregatorservice.service.impl;

import lombok.AllArgsConstructor;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.entity.Tool;
import masters.aggregatorservice.repository.RuleViolationRepository;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.LanguageQueryService;
import masters.aggregatorservice.service.RuleViolationCommandService;
import masters.aggregatorservice.service.ToolQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleViolationCommandServiceImpl implements RuleViolationCommandService {

    private final RuleViolationRepository ruleViolationRepository;

    private final ToolQueryService toolQueryService;

    private final LanguageQueryService languageQueryService;

    private final RuleViolationQueryServiceImpl ruleViolationQueryService;

    @Override
    @Transactional
    public Set<RuleViolation> createFromSarifForLanguage(Sarif sarif, String languageName) {
        final Set<RuleViolation> ruleViolations = new HashSet<>();

        sarif.getRuns().forEach(run -> {
            final String toolName = run.getTool().getDriver().getName();
            final Tool tool = toolQueryService.findByName(toolName);

            run.getResults().forEach(result -> {
                final String ruleName = result.getRuleId();
                final Optional<RuleViolation> optionalRuleViolation = ruleViolationQueryService.findRuleViolation(ruleName, toolName, languageName);

                if (optionalRuleViolation.isPresent()) {
                    ruleViolations.add(optionalRuleViolation.get());

                    return;
                }

                final Language language = languageQueryService.findByName(languageName);

                final RuleViolation ruleViolation = new RuleViolation();
                ruleViolation.setRuleSarifId(ruleName);
                ruleViolation.setTool(tool);
                ruleViolation.setLanguage(language);

                ruleViolations.add(ruleViolationRepository.save(ruleViolation));
            });
        });

        return ruleViolations;
    }

}

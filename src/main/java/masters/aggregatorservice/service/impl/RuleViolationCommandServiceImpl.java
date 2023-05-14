package masters.aggregatorservice.service.impl;

import lombok.AllArgsConstructor;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.entity.RuleViolation;
import masters.aggregatorservice.repository.RuleViolationRepository;
import masters.aggregatorservice.schema.Result;
import masters.aggregatorservice.schema.Run;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.RuleViolationCommandService;
import masters.aggregatorservice.service.ToolQueryService;
import masters.aggregatorservice.service.dto.Triple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@AllArgsConstructor
public class RuleViolationCommandServiceImpl implements RuleViolationCommandService {

    private final RuleViolationRepository ruleViolationRepository;

    private final ToolQueryService toolQueryService;

    private final RuleViolationQueryServiceImpl ruleViolationQueryService;

    private final ConcurrentLinkedQueue<Triple<String, String, Language>> scheduledRuleViolations = new ConcurrentLinkedQueue<>();

    @Override
    public void scheduleRuleViolationUpdateFromSarifForLanguage(Sarif sarif, Language language) {
        for (Run run : sarif.getRuns()) {
            final String toolName = run.getTool().getDriver().getName();

            for (Result result : run.getResults()) {
                final String ruleName = result.getRuleId();

                scheduledRuleViolations.add(new Triple<>(ruleName, toolName, language));
            }
        }
    }

    @Override
    @Transactional
    public void updateScheduledRuleViolations(int processingSize) {
        final List<RuleViolation> ruleViolations = new ArrayList<>(processingSize);
        final Map<String, Map<String, RuleViolation>> existingRuleViolations = new HashMap<>();

        for (int i = 0; i < processingSize; i++) {
            final Triple<String, String, Language> poll = scheduledRuleViolations.poll();

            if (Objects.isNull(poll)) {
                break;
            }

            final String ruleSarifId = poll.getFirst();
            final String toolName = poll.getSecond();
            final Language language = poll.getThird();

            Map<String, RuleViolation> ruleViolationPerToolMap = existingRuleViolations.computeIfAbsent(language.getName(), s -> new HashMap<>());
            RuleViolation ruleViolation = ruleViolationPerToolMap.get(toolName);

            if (ruleViolation == null) {
                final Optional<RuleViolation> possiblySavedRuleViolation = ruleViolationQueryService.findRuleViolation(ruleSarifId, toolName, language);
                ruleViolation = possiblySavedRuleViolation.orElseGet(
                    () -> {
                        final RuleViolation newRuleViolation = new RuleViolation();

                        newRuleViolation.setRuleSarifId(ruleSarifId);
                        newRuleViolation.setTool(toolQueryService.findByName(toolName));
                        newRuleViolation.setLanguage(language);
                        newRuleViolation.setOccurrence(0L);

                        return newRuleViolation;
                    }
                );

                ruleViolationPerToolMap.put(toolName, ruleViolation);
                existingRuleViolations.put(language.getName(), ruleViolationPerToolMap);

                ruleViolations.add(ruleViolation);
            }

            ruleViolation.setOccurrence(ruleViolation.getOccurrence() + 1);
        }

        ruleViolationRepository.saveAll(ruleViolations);
    }

}

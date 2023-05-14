package masters.aggregatorservice.scheduled;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.service.RuleViolationCommandService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final int PROCESSING_SIZE = 2000;

    private final RuleViolationCommandService ruleViolationCommandService;

    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void scheduledUpdateRuleViolationsTask() {
        ruleViolationCommandService.updateScheduledRuleViolations(PROCESSING_SIZE);
    }

}

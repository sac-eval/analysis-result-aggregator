package masters.aggregatorservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.AnalysisSagaService;
import masters.aggregatorservice.service.command.AnalysisCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisSagaService analysisSagaService;

    @PostMapping
    public Sarif analyse(@Valid @RequestBody AnalysisCommand analysisCommand) {
        return analysisSagaService.analyse(analysisCommand);
    }

}

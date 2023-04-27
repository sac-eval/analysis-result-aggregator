package masters.aggregatorservice.controller;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.controller.response.LanguageResponse;
import masters.aggregatorservice.mapper.LanguageMapper;
import masters.aggregatorservice.service.LanguageQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageQueryService languageQueryService;

    @GetMapping
    public List<LanguageResponse> getAllSupportedLanguages() {
        return LanguageMapper.INSTANCE.languageToLanguageResponse(languageQueryService.findAll());
    }

}

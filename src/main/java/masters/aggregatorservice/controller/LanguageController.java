package masters.aggregatorservice.controller;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.controller.response.LanguageResponse;
import masters.aggregatorservice.mapper.LanguageMapper;
import masters.aggregatorservice.service.LanguageQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageQueryService languageQueryService;

    private final LanguageMapper languageMapper;

    @GetMapping
    public List<LanguageResponse> findByQuery(@RequestParam(required = false) String name, @RequestParam(required = false) String extension) {
        return languageMapper.languageListToLanguageResponseList(languageQueryService.findByQuery(name, extension));
    }

    @GetMapping("/{extension}")
    public LanguageResponse findByExtension(@PathVariable(required = false) String extension) {
        return languageMapper.languageToLanguageResponse(languageQueryService.findByExtension(extension));
    }

}

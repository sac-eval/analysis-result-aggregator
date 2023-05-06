package masters.aggregatorservice.controller;

import lombok.RequiredArgsConstructor;
import masters.aggregatorservice.controller.response.ToolResponse;
import masters.aggregatorservice.mapper.ToolMapper;
import masters.aggregatorservice.service.ToolQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tools")
@RequiredArgsConstructor
public class ToolController {

    private final ToolQueryService toolQueryService;

    @GetMapping
    public List<ToolResponse> findAllByQuery(@RequestParam(required = false) String language) {
        if (Objects.nonNull(language)) {
            return ToolMapper.INSTANCE.toolToToolResponses(toolQueryService.findAllByLanguageName(language));
        } else {
            return ToolMapper.INSTANCE.toolToToolResponses(toolQueryService.findAll());
        }
    }

}

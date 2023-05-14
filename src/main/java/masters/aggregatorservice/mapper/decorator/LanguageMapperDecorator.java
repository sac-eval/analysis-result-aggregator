package masters.aggregatorservice.mapper.decorator;

import masters.aggregatorservice.controller.response.LanguageResponse;
import masters.aggregatorservice.controller.response.ToolResponse;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.mapper.LanguageMapper;
import masters.aggregatorservice.service.AverageLatencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

public abstract class LanguageMapperDecorator implements LanguageMapper {

    @Autowired
    @Qualifier("delegate")
    private LanguageMapper delegate;

    @Autowired
    private AverageLatencyService averageLatencyService;

    @Override
    public LanguageResponse languageToLanguageResponse(Language language) {
        final LanguageResponse languageResponse = delegate.languageToLanguageResponse(language);

        if (languageResponse == null) {
            return null;
        }

        for (ToolResponse toolResponse : languageResponse.getTools()) {
            Optional<Double> optionalLatency = averageLatencyService.calculateLatency(language.getName(), toolResponse.getName());

            optionalLatency.ifPresent(toolResponse::setScanTime);
        }

        return languageResponse;
    }

    @Override
    public List<LanguageResponse> languageListToLanguageResponseList(List<Language> languages) {
        if (languages == null) {
            return null;
        }

        return languages.stream().map(this::languageToLanguageResponse).toList();
    }

}

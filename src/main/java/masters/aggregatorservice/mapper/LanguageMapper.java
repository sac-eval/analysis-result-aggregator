package masters.aggregatorservice.mapper;

import masters.aggregatorservice.controller.response.LanguageResponse;
import masters.aggregatorservice.entity.Language;
import masters.aggregatorservice.mapper.decorator.LanguageMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(LanguageMapperDecorator.class)
public interface LanguageMapper {

    @Mapping(target = "Tool.scanTime", ignore = true)
    LanguageResponse languageToLanguageResponse(Language language);

    List<LanguageResponse> languageListToLanguageResponseList(List<Language> languages);

}

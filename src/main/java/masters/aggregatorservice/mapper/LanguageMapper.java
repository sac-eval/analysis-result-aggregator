package masters.aggregatorservice.mapper;

import masters.aggregatorservice.controller.response.LanguageResponse;
import masters.aggregatorservice.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    LanguageResponse languageToLanguageResponse(Language language);

    List<LanguageResponse> languageListToLanguageResponseList(List<Language> languages);

}

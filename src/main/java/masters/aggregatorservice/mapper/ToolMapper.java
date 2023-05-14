package masters.aggregatorservice.mapper;

import masters.aggregatorservice.controller.response.ToolResponse;
import masters.aggregatorservice.entity.Tool;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ToolMapper {

    @Mapping(target = "scanTime", ignore = true)
    List<ToolResponse> toolToToolResponses(List<Tool> tools);

}

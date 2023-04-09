package masters.aggregatorservice.mapper;

import masters.aggregatorservice.controller.response.ToolResponse;
import masters.aggregatorservice.entity.Tool;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ToolMapper {

    ToolMapper INSTANCE = Mappers.getMapper(ToolMapper.class);

    List<ToolResponse> toolToToolResponses(List<Tool> tools);

}

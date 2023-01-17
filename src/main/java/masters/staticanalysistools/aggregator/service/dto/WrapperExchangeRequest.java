package masters.staticanalysistools.aggregator.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WrapperExchangeRequest {

    private String code;

    private Boolean encoded;

    private String language;

}

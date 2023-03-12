package masters.aggregatorservice.service.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

@Builder
@Data
public class WrapperExchangeRequest {

    private String code;

    private Boolean encoded;

    private String language;

}

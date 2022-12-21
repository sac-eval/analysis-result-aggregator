package masters.staticanalysistools.aggregator.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExchangeServiceResponse<T> {

    private LocalDateTime executionTime;

    private T result;

}

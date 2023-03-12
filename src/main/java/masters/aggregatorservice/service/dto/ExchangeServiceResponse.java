package masters.aggregatorservice.service.dto;

import lombok.Data;
import masters.aggregatorservice.schema.Sarif;

import java.time.LocalDateTime;

@Data
public class ExchangeServiceResponse<T> {

    private LocalDateTime executionTime;

    private T result;

}

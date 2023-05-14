package masters.aggregatorservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimedResult<T> {

    private double time;

    private T result;

}

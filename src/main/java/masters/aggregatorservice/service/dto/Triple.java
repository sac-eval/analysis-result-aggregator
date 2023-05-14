package masters.aggregatorservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triple<T, U, V> {

    private T first;

    private U second;

    private V third;

}

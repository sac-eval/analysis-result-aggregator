package masters.staticanalysistools.aggregator.service.command;

import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.util.Collection;

@Data
@Builder
public class ParallelExchangeCommand<T> {

    private Collection<URI> urls;

    private T body;

}

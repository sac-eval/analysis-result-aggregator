package masters.staticanalysistools.aggregator.service.impl;

import masters.staticanalysistools.aggregator.schema.ExternalProperties;
import masters.staticanalysistools.aggregator.schema.Run;
import masters.staticanalysistools.aggregator.schema.Sarif;
import masters.staticanalysistools.aggregator.service.AggregationService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AggregationServiceImpl implements AggregationService {

    private static final URI schema =
        URI.create("https://schemastore.azurewebsites.net/schemas/json/sarif-2.1.0-rtm.4.json");

    @Override
    public Sarif aggregate(List<Sarif> sarifList) {
        final Sarif sarif = new Sarif();
        sarif.set$schema(schema);
        sarif.setVersion(Sarif.Version._2_1_0);

        final List<Run> aggregatedRuns =
            sarifList.stream().flatMap(intermediate -> intermediate.getRuns().stream()).collect(Collectors.toList());
        sarif.setRuns(aggregatedRuns);

        final Set<ExternalProperties> aggregatedInlineExternalProperties =
            sarifList.stream().flatMap(intermediate -> intermediate.getInlineExternalProperties().stream())
                .collect(Collectors.toSet());
        sarif.setInlineExternalProperties(aggregatedInlineExternalProperties);

        return sarif;
    }

}

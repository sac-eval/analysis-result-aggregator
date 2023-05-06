package masters.aggregatorservice.service.command;

import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.util.Collection;

@Data
@Builder
public class SarifExchangeCommand {

    private Collection<URI> urls;

    private String code;

    private boolean encoded;

    private String extension;

}

package masters.aggregatorservice.config;

import masters.aggregatorservice.schema.Sarif;
import masters.aggregatorservice.service.dto.ExchangeServiceResponse;
import masters.aggregatorservice.service.dto.WrapperExchangeRequest;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.LinkedHashSet;

@Configuration
@ImportRuntimeHints(NativeConfig.class)
@RegisterReflectionForBinding({Sarif.class, WrapperExchangeRequest.class, ExchangeServiceResponse.class})
public class NativeConfig implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.serialization().registerType(LinkedHashSet.class);
        hints.reflection().registerType(LinkedHashSet.class);
    }
}

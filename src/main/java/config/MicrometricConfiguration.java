package config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometricConfiguration {

    @Bean
    MeterRegistryCustomizer meterRegistryCustomizer(MeterRegistry meterRegistry) {

        return meterRegistry1 -> {
            meterRegistry.config()
                    .commonTags("application", "micrometer");
        };

//        PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
//        prometheusRegistry.config().meterFilter(new PrometheusRenameFilter());
//        Counter counter = Counter
//                .builder("instance")
//                .description("indicates instance count of the object")
//                .tags("dev", "performance")
//                .register(prometheusRegistry);
    }

}

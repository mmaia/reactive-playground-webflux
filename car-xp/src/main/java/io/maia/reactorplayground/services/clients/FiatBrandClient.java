package io.maia.reactorplayground.services.clients;

import io.maia.reactorplayground.config.BrandClientConfig;
import io.maia.reactorplayground.sharedkernel.dto.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
public class FiatBrandClient implements BrandClient {

    private final WebClient webClient;
    private final BrandClientConfig brandClientConfig;

    private static final SecureRandom random = new SecureRandom();

    public FiatBrandClient(WebClient.Builder webClientBuilder, BrandClientConfig brandClientConfig) {
        this.webClient = webClientBuilder.baseUrl(brandClientConfig.getBrandServiceUrl()).build();
        this.brandClientConfig = brandClientConfig;

        log.info("Client properties settings are, \nGlobal Max Wait: {}", brandClientConfig.getMaxWaitForFullProcessingInMillis());
        log.info("\nFiat: \nRandom: {}, \nMax Wait for Response {}",
                brandClientConfig.getFiat().isRandom(),
                brandClientConfig.getFiat().getMaxWaitForResponseInMillis());
    }

    @Override
    public Mono<List<Car>> search() {
        log.debug("FiatBrandClient.search");
        return this.webClient.get()
                .uri("/fiat")
                .retrieve()
                .bodyToFlux(Car.class)
                .collectList()
                .timeout(Duration.of(waitTime(), ChronoUnit.MILLIS))
                .doOnError((throwable) -> log.error("Timed out Fiat search", throwable));
    }

    private int waitTime() {
        int result = brandClientConfig.getFiat().getMaxWaitForResponseInMillis();
        if (brandClientConfig.getFiat().isRandom()) {
            result = random.nextInt(result);
        }
        return result;
    }

}

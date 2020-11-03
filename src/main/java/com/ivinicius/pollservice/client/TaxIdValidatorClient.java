package com.ivinicius.pollservice.client;

import com.ivinicius.pollservice.client.response.TaxValidatorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class TaxIdValidatorClient {

    private WebClient webClient = WebClient.create();

    private String baseUrl = "https://user-info.herokuapp.com";

    public Mono<TaxValidatorResponse> validateCpf(String taxId) {
        return webClient.get()
                .uri(this.getBaseUrl()
                        .pathSegment("users")
                        .pathSegment(taxId)
                        .toUriString())
                .retrieve()
                .bodyToMono(TaxValidatorResponse.class);
    }

    private UriComponentsBuilder getBaseUrl() {
        return UriComponentsBuilder
                .fromUriString(baseUrl);
    }
}

package dev.biswajit.ecomm.productservice.thirdparty;

import dev.biswajit.ecomm.productservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class FakeStoreClient {
    private final WebClient webclient;

    public FakeStoreClient(@Autowired WebClient.Builder builder, @Value("${app.fakestore.url}") String baseUrl) {
        this.webclient = builder.baseUrl(baseUrl).build();
    }

    public Mono<ThirdPartyProductDto> getProductBy(Long id) {
        return webclient
                .get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ThirdPartyProductDto.class)
                .log();
    }

    public Mono<List<ThirdPartyProductDto>> getAllProducts() {
        return webclient
                .get()
                .uri("/products")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ThirdPartyProductDto>>() {
                })
                .log();
    }

    public Mono<ThirdPartyProductDto> add(ProductDto newProduct) {
        return webclient
                .post()
                .uri("/products")
                .body(BodyInserters.fromValue(newProduct))
                .retrieve()
                .bodyToMono(ThirdPartyProductDto.class)
                .log();
    }

    //    todo - refactor to pass third party product dto instead of ProductDto
    public Mono<ThirdPartyProductDto> updateBy(Long id, ProductDto updateProductDto) {
        return webclient
                .put()
                .uri("/products/{id}", id)
                .body(BodyInserters.fromValue(updateProductDto))
                .retrieve()
                .bodyToMono(ThirdPartyProductDto.class)
                .log();
    }

    public Mono<ThirdPartyProductDto> deleteBy(Long id) {
        return webclient
                .delete()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ThirdPartyProductDto.class)
                .log();
    }
}

package dev.biswajit.ecomm.productservice.service;

import dev.biswajit.ecomm.productservice.thirdparty.FakeStoreClient;
import dev.biswajit.ecomm.productservice.thirdparty.ThirdPartyProductDto;
import dev.biswajit.ecomm.productservice.dto.ProductDto;
import dev.biswajit.ecomm.productservice.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service("FAKE_STORE_SERVICE")
@ConditionalOnProperty(name = "app.service.type", havingValue = "FAKE_STORE_SERVICE")
public class FakeStoreProductService implements ProductService {

    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Mono<ProductDto> productBy(Long id) {
        return fakeStoreClient.getProductBy(id)
                .switchIfEmpty(Mono.error(ProductNotFoundException.with("no product with id " + id + " found")))
                .map(product ->
                        new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory(),
                                product.getDescription(), product.getImageUrl()))
                .log();
    }

    @Override
    public Mono<List<ProductDto>> allProducts() {
        return fakeStoreClient.getAllProducts()
                .flatMap(list -> {
                    List<ProductDto> products = list.stream().map(it -> new ProductDto(it.getId(), it.getTitle(), it.getPrice(), it.getCategory(),
                            it.getDescription(), it.getImageUrl()
                    )).toList();
                    return Mono.just(products);
                });
    }

    @Override
    public Mono<ProductDto> add(ProductDto newProduct) {

        return fakeStoreClient.add(newProduct)
                .map(it ->
                        new ProductDto(it.getId(), it.getTitle(), it.getPrice(), it.getCategory(), it.getDescription(),
                                it.getImageUrl())).log();
    }

    @Override
    public Mono<ProductDto> deleteBy(Long id) {

        return fakeStoreClient.deleteBy(id)
                .map(it ->
                        new ProductDto(it.getId(), it.getTitle(), it.getPrice(), it.getCategory(), it.getDescription(),
                                it.getImageUrl())).log();
    }

    @Override
    public Mono<ProductDto> updateBy(Long id, ProductDto updateProductDto) {

        return fakeStoreClient.updateBy(id, updateProductDto)
                .map(it ->
                        new ProductDto(it.getId(), it.getTitle(), it.getPrice(), it.getCategory(),
                                it.getDescription(), it.getImageUrl())).log();
    }
}

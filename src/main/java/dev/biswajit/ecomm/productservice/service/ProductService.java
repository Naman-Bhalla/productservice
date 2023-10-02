package dev.biswajit.ecomm.productservice.service;

import dev.biswajit.ecomm.productservice.dto.ProductDto;
import dev.biswajit.ecomm.productservice.exception.ProductNotFoundException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Mono<ProductDto> productBy(Long id) throws ProductNotFoundException;

    Mono<List<ProductDto>> allProducts();

    Mono<ProductDto> add(ProductDto newProduct);

    Mono<ProductDto> deleteBy(Long id) throws ProductNotFoundException;

    Mono<ProductDto> updateBy(Long id, ProductDto updateProductDto) throws ProductNotFoundException;

}

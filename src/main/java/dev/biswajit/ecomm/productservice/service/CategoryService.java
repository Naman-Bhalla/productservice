package dev.biswajit.ecomm.productservice.service;

import dev.biswajit.ecomm.productservice.dto.ProductDto;
import dev.biswajit.ecomm.productservice.model.Category;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CategoryService {
    Mono<List<Category>> allCategories();

    Mono<List<ProductDto>> findProductsByCategory(String category);
}

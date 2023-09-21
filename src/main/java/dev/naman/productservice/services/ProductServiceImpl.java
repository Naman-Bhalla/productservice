package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductServiceImpl {
    // abstract method for create product
    GenericProductDto createProduct(GenericProductDto product);

    // abstract method for get product by id
    GenericProductDto getProductById(UUID id) throws NotFoundException;

    // get all products
    List<GenericProductDto> getAllProducts();

    // abstract method to delete product by id
    GenericProductDto deleteProductById(UUID id);

    // abstract method for update product by id
    GenericProductDto updatePrductById(GenericProductDto genericProductDto,UUID id);

    // get all categoris of a type
    List<ProductDto> getCategoryById(String categoryName);

    List<String> getAllCategories();
}

package dev.bhanu.productservice.services;

import dev.bhanu.productservice.Exception.NotFoundException;
import dev.bhanu.productservice.dtos.SelfGenericProductDto;
import dev.bhanu.productservice.dtos.ProductDto;

import java.util.List;

public interface SelfProductService {
    ProductDto createProduct(SelfGenericProductDto product);
    ProductDto getProductById(String id) throws NotFoundException;

    ProductDto updateProductById(String id, ProductDto product) throws NotFoundException;

    List<ProductDto> getAllProduct();

    List<ProductDto> getAllProductByCategory(String name) throws NotFoundException;

    void deleteProductById(String id) throws NotFoundException;
}

package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    GenericProductDto createProduct(Product product);

    GenericProductDto getProductById(String id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    void updateProductById(UUID id, Product updateProduct);
    void deleteProductById(UUID id);
}

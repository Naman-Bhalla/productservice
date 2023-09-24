package dev.naman.productservice.services;

import java.util.List;
import java.util.UUID;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(UUID id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(UUID id);

    GenericProductDto updateProductById(String id, GenericProductDto product);

    List<GenericProductDto> getProductsInCategory(List<String> categoryId);
}

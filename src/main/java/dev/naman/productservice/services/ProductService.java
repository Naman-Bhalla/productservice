package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(UUID id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(UUID id) throws NotFoundException;

    GenericProductDto updateProduct(UUID id, GenericProductDto product) throws NotFoundException;

    List<String> getProductCategories();

    List<GenericProductDto> getProductInCategory(String categoryName);
}

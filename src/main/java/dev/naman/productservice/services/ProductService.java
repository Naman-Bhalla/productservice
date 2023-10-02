package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<GenericProductDto> getAllProducts() throws NotFoundException;
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto getProductById(UUID id) throws NotFoundException;
    GenericProductDto deleteProductById(Long id) throws NotFoundException;
    GenericProductDto deleteProductById(UUID id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto updateProductById(Long id, GenericProductDto product) throws NotFoundException;
    GenericProductDto updateProductById(UUID id, GenericProductDto product) throws NotFoundException;
}
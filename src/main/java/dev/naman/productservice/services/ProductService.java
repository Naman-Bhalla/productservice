package dev.naman.productService.services;

import dev.naman.productService.dtos.GenericProductDto;
import dev.naman.productService.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;


public interface ProductService {

    GenericProductDto getProductById(UUID id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(UUID id) throws NotFoundException;

    GenericProductDto updateProductById(GenericProductDto product, UUID id) throws NotFoundException;
}

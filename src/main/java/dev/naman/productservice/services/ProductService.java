package dev.naman.productservice.services;

import dev.pranay.productservice.dtos.GenericProductDto;
import dev.pranay.productservice.exception.NotFoundException;

import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);
    GenericProductDto updateProductById(Long id, GenericProductDto Product);
}

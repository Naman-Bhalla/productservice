package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id);

    List<GenericProductDto> getAllProducts();
}

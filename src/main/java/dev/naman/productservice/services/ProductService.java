package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts() throws NotFoundException;

   GenericProductDto deleteProduct(Long id) throws NotFoundException;

    GenericProductDto updateProduct(Long id, GenericProductDto product) throws NotFoundException;
}

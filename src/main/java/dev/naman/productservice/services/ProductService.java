package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(String id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

   GenericProductDto deleteProduct(String id);
}

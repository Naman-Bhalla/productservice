package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    String createProduct(GenericProductDto product);

    GenericProductDto getProductById(String idStr) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

   String deleteProduct(String idStr) throws NotFoundException;

    GenericProductDto updateProduct(GenericProductDto product) throws NotFoundException;
}

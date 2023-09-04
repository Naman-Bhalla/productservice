package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Product;

public interface ProductService {

    GenericProductDto getProductById(Long id);
}

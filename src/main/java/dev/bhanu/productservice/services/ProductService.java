package dev.bhanu.productservice.services;

import dev.bhanu.productservice.Exception.NotFoundException;
import dev.bhanu.productservice.dtos.FakeStoreProductDto;
import dev.bhanu.productservice.dtos.GenericProductDto;
import dev.bhanu.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto updateProductById(Long id, GenericProductDto product);

    List<GenericProductDto> getAllProduct();


}

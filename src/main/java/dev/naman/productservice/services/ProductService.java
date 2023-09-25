package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDto createProduct(Product product);

    ProductDto getProductById(String id) throws NotFoundException;
    List<ProductDto> getProductsInCategory(String id) throws NotFoundException;

    List<ProductDto> getAllProducts(List<String> categories);

    ProductDto deleteProduct(String id);
    ProductDto updateProduct(ProductDto productDto,String id);
}

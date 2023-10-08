package dev.naman.productservice.services;

import dev.naman.productservice.dtos.*;
import java.util.List;

public interface SelfProductService {
    CreateProductResponseDto createProduct(CreateProductRequestDto product);
    GetProductResponseDto getProductById(Long id);
    GetAllProductResponseDto getAllProducts();
    GetProductResponseDto updateProductById(CreateProductRequestDto request, Long id);
    DeleteProductResponseDto deleteProductById(Long id);
    GetAllProductResponseDto getProductsByCategory(String category);
    List<CategoryTypeDto> getAllCategories();


}

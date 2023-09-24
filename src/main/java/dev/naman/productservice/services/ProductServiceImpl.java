package dev.naman.productservice.services;
import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.models.Category;
import java.util.List;
import java.util.UUID;
public interface ProductServiceImpl {

    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(UUID id) throws NotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(UUID id);
    GenericProductDto updateProductById(GenericProductDto genericProductDto, UUID id);
    List<ProductDto> getCategoryById(String categoryName);
    List<String> getAllCategories();

}

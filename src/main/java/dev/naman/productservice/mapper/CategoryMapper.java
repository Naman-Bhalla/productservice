package dev.naman.productservice.mapper;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;

import java.util.List;

public class CategoryMapper {

    public static CategoryDto convertCategoryEntityToCategoryDto(Category category) {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        List<ProductDto> productDtoList = category.getProducts().stream()
                .map(CategoryMapper::convertCategoryProductToProduct).toList();

        categoryDto.setProducts(productDtoList);

        return categoryDto;
    }
    private static ProductDto convertCategoryProductToProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice().getPrice());

        return productDto;
    }
}

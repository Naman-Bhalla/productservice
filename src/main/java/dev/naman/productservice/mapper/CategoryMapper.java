package dev.naman.productservice.mapper;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDto convertCategoryEntityToCategoryDto(Category category) {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        List<ProductDto> productDtoList = category.getProducts().stream().map(product ->
                convertCategoryProductToProduct(product)).collect(Collectors.toList());

        categoryDto.setProducts(productDtoList);

        return categoryDto;
    }

    private static ProductDto convertCategoryProductToProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    public static Product convertProductDtoToProductEntity(GenericProductDto productDto, Optional<Category> categoryOptional) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());

        Category category = categoryOptional.isPresent()?categoryOptional.get():new Category(productDto.getCategory(), new ArrayList<>());
        List<Product> categoryProductList = category.getProducts();
        categoryProductList.add(product);
        category.setProducts(categoryProductList);

        product.setCategory(category);
        product.setPrice(new Price("INR", productDto.getPrice()));

        return product;
    }
}

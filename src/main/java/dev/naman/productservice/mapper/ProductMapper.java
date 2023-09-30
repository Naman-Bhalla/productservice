package dev.naman.productservice.mapper;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductMapper {

    public static GenericProductDto convertProductEntityToGenericProduct(Product product) {

        GenericProductDto productDto = new GenericProductDto();
        productDto.setId(product.getUuid().toString());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setCategory(product.getCategory().getName());
        productDto.setPrice(product.getPrice().getPrice());
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

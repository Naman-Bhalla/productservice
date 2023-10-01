package dev.naman.productservice.dtos.convertor;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;

public class ProductConvertor {

    public static GenericProductDto getGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setUuid(product.getUuid());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setImage(product.getImage());

        return genericProductDto;
    }

    public static Product getProductDto(GenericProductDto genericProductDto){
        Product product = new Product();

        product.setUuid(genericProductDto.getUuid());
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        Category category = new Category();
        category.setName(genericProductDto.getCategory());
        product.setCategory(category);
        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        product.setPrice(price);
        product.setImage(genericProductDto.getImage());

        return product;
    }
}

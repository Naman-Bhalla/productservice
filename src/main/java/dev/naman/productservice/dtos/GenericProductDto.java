package dev.naman.productservice.dtos;

import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public static GenericProductDto from(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setTitle(product.getTitle());
        return genericProductDto;
    }
}

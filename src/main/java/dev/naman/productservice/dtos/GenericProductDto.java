package dev.naman.productservice.dtos;

import dev.naman.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private Double price;
    private String currency;
}

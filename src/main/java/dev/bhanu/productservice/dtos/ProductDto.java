package dev.bhanu.productservice.dtos;

import dev.bhanu.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductDto {
    UUID uuid;
    String title;
    String image;
    String price;
    String category;
}

package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {
    private String title;
    private String description;
    private String category;
    private String image;
    private double price;
}

package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}

package dev.naman.productservice.dtos;

import dev.naman.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private UUID uuid;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}

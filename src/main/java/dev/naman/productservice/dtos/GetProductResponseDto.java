package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductResponseDto {
    private Long id;
    private String title;
    private String category;
    private String description;
    private String image;
    private double price;
}

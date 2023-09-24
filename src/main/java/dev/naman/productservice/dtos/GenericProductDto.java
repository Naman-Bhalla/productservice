package dev.naman.productservice.dtos;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}

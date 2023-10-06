package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class GenericProductDtoDB {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private String category;
    private Double price;
    private String currency;

}

package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class GenericCategoryDto {
    private String id;
    private String name;
    private List<GenericProductDto> products;
}

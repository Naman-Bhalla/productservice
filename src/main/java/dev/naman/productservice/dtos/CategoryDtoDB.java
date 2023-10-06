package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDtoDB {
    private String id;
    private String name;
    private List<GenericProductDtoDB> products;
}

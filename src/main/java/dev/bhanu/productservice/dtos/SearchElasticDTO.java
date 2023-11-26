package dev.bhanu.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchElasticDTO {
    private String query;
    private int pageNumber;
    private int pageSize;
}

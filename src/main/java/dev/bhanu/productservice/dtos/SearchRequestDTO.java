package dev.bhanu.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    private String query;
    private int pageNumber;
    private int pageSize;
    private List<SortParameter> sortParameters;
}

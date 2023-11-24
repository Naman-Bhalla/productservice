package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParameter {
    private String parameterName;
    // "ASC" if ascending "DESC" if descending
    private String sortType;
}

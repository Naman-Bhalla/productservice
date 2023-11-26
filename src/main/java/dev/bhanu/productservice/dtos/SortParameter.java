package dev.bhanu.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParameter {
    private String parameter;
    private SortType sortType;
}

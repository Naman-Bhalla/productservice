package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private int pageNumber;
    private int sizeOfEachPage;
    private List<SortParameter> sortByParameters;
}

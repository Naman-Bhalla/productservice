package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.SearchRequestDto;
import dev.naman.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.searchProducts(
                searchRequestDto.getQuery(), searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfEachPage(), searchRequestDto.getSortByParameters()
        );
    }
}

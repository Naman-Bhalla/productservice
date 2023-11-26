package dev.bhanu.productservice.controllers;

import dev.bhanu.productservice.dtos.SearchRequestDTO;
import dev.bhanu.productservice.dtos.SelfGenericProductDto;
import dev.bhanu.productservice.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SelfGenericProductDto> searchProducts(@RequestBody SearchRequestDTO searchRequestDTO){
        return searchService.searchProducts(searchRequestDTO);
    }
}

package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GetProductTitlesRequestDto;

import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // #4 - Get in category
    @GetMapping("/{uuid}")
    public CategoryDto getCategory(@PathVariable("uuid") String uuid) throws NotFoundException {
        return categoryService.getCategory(uuid);

    }


    // #3 - Get all Categories
    @GetMapping
    public List<CategoryDto> getAllCategory(@RequestParam("name") String name) throws NotFoundException {
        if (StringUtils.isNotEmpty(name)) {
            // #4.1 - Get in category by Name
            return categoryService.getCategoryByName(name);
        }

        return categoryService.getAllCategories();
    }

    @GetMapping("/titles/")
    public List<String> getProductTitles(@RequestBody GetProductTitlesRequestDto requestDto) {

        List<String> uuids = requestDto.getUuids();

        return categoryService.getProductTitles(uuids);
    }
}

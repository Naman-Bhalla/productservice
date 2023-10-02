package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericCategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<GenericCategoryDto> getAllCategories() throws NotFoundException{
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public List<GenericProductDto> getProductsByCategory(@PathVariable("id") String id) throws NotFoundException{
        return categoryService.getProductByCategory(UUID.fromString(id));
    }
}
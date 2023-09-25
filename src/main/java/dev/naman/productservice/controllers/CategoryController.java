package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericCategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<GenericCategoryDto> getAllCategories() throws NotFoundException{
        List<GenericCategoryDto> genericCategories = categoryService.getAllCategories();
        return genericCategories;
    }
    @GetMapping("/{id}")
    public List<GenericProductDto> getProductsByCategory(@PathVariable("id") String id) throws NotFoundException{
        List<GenericProductDto> products = categoryService.getProductByCategory(UUID.fromString(id));
        return products;
    }
}

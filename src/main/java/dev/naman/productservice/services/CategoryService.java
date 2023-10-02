package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategory(String uuid) throws NotFoundException;
    List<String> getProductTitles(List<String> categoryUUIDs);

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategoryByName(String name) throws NotFoundException;
}

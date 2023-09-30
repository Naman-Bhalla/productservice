package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(String uuid);
    List<String> getProductTitles(List<String> categoryUUIDs);

    List<CategoryDto> getAllCategories();
}

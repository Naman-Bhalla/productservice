package dev.naman.productservice.services;

import java.util.List;

import dev.naman.productservice.models.Category;

public interface CategoryService {
    Category getCategory(String uuid);

    List<String> getProductTitles(List<String> categoryUUIDs);

    Category createCategory(String name);

    List<Category> getAllCategories();

    List<String> getAllCategoryNames();

}

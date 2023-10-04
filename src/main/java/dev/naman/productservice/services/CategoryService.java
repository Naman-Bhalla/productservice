package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;

import java.util.List;

public interface CategoryService {

    Category getAllProductsFromACategory(String uuid) throws NotFoundException;

    List<CategoryDto> getAllCategories();

//    List<String> getProductTitles(List<String> categoryUUIDs);
}

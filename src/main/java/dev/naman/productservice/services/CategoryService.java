package dev.naman.productService.services;

import dev.naman.productService.dtos.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

  List<CategoryDto> getAllCategories();

  CategoryDto getCategoryById(UUID id);
}

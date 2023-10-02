package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericCategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<GenericCategoryDto> getAllCategories() throws NotFoundException;
    List<GenericProductDto> getProductByCategory(UUID uuid) throws NotFoundException;
}
package dev.bhanu.productservice.services;

import dev.bhanu.productservice.dtos.SelfCategoryDto;

import java.util.List;

public interface CategoryService {
    public List<SelfCategoryDto> getAllCategory();
}

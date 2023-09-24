package dev.naman.productService.services;

import dev.naman.productService.dtos.CategoryDto;
import dev.naman.productService.dtos.GenericProductDto;
import dev.naman.productService.models.Category;
import dev.naman.productService.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setDescription(category.getDescription());
        categoryDto.setName(category.getName());
        categoryDto.setProducts(new ArrayList<>());
        category.getProducts().forEach(product -> {
            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setId(product.getId());
            genericProductDto.setTitle(product.getTitle());
            genericProductDto.setPrice(product.getPrice().getPrice());
            categoryDto.getProducts().add(genericProductDto);
        });
        return categoryDto;
    }
}

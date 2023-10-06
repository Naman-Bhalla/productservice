package dev.naman.productservice.services;

import dev.pranay.productservice.models.Category;
import dev.pranay.productservice.repositories.CategoryRepository;
import dev.pranay.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryServiceDB{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(String uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));

        if (categoryOptional.isEmpty()) {
            return null;
        }

        Category category = categoryOptional.get();

//        List<Product> products = category.getProducts();

        return category;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        for(Category category : categoryList)
            categories.add(category.getName());
        return categories;
    }
}

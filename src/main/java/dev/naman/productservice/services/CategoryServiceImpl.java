package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        // Get all categories from the database
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setUuid(category.getUuid());
            categoryDto.setName(category.getName());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
        // USE THIS TO RETURN A SET OF ALL UNIQUE CATEGORIES
        //        List<Category> category = categoryRepository.findAll();
//        Set<String> all_cat = new HashSet<>();
//        for (Category cat : category) {
//            all_cat.add(cat.getName());
//        }
//        return all_cat;
    }

    // Get category by String
    @Override
    public Category getAllProductsFromACategory(String uuid) throws NotFoundException {

        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));

        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("Category with ID : " + uuid + " NOT FOUND. Fetching Category Failed.");
        }
        Category category = categoryOptional.get();
        List<Product> products = category.getProducts();
        return category;
    }


    //
//    public List<String> getProductTitles(List<String> categoryUUIDs) {
//        List<UUID> uuids = new ArrayList<>();
//
//        for (String uuid : categoryUUIDs) {
//            uuids.add(UUID.fromString(uuid));
//        }
//        List<Category> categories = categoryRepository.findAllById(uuids);
//        List<Product> products = productRepository.findAllByCategoryIn(categories);
//        List<String> titles = new ArrayList<>();
//        for (Product p: products) {
//            titles.add(p.getTitle());
//        }
//        return titles;
//    }
}

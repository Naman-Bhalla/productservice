package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.mapper.CategoryMapper;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CategoryDto getCategory(String uuid) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));

        if (categoryOptional.isEmpty()) {
            throw new NotFoundException(uuid + " catgeoryId is not in DB");
        }

        return CategoryMapper.convertCategoryEntityToCategoryDto(categoryOptional.get());
    }

    public List<String> getProductTitles(List<String> categoryUUIDs) {
        List<UUID> uuids = new ArrayList<>();

        for (String uuid: categoryUUIDs) {
            uuids.add(UUID.fromString(uuid));
        }
//
//        List<Category> categories = categoryRepository.findAllById(uuids);
//
//
//        List<String> titles = new ArrayList<>();
//
//        categories.forEach(
//                category -> {
//                    category.getProducts().forEach(
//                            product -> {
//                                titles.add(product.getTitle());
//                            }
//                    );
//                }
//        );
//
//
//        return titles;

        List<Category> categories = categoryRepository.findAllById(uuids);

        List<Product> products = productRepository.findAllByCategoryIn(categories);

        List<String> titles = new ArrayList<>();

        for (Product p: products) {
            titles.add(p.getTitle());
        }

        return titles;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        List<CategoryDto> categoryDtoList = categoryList.stream()
                                .map(CategoryMapper::convertCategoryEntityToCategoryDto).toList();

        return categoryDtoList;
    }

    @Override
    public List<CategoryDto> getCategoryByName(String name) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        if(categoryOptional.isEmpty()) {
            throw new NotFoundException(name + " catgeory is not in DB");
        }
        return List.of(CategoryMapper.convertCategoryEntityToCategoryDto(categoryOptional.get()));
    }
}

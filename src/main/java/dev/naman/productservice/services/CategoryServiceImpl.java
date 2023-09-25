package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.ProductDto;
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

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Category getCategory(String uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));

        if (categoryOptional.isEmpty()) {
            return null;
        }

        Category category = categoryOptional.get();

        List<Product> products = category.getProducts();


        return category;
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
    public List<CategoryDto> getAllCategories(List<String> categoryUUIds) {
        List<UUID> uuids = new ArrayList<>();
        for(String categoryId: categoryUUIds){
            uuids.add(UUID.fromString(categoryId));
        }
        List<Category> categories = categoryRepository.findAllById(uuids);
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category category:categories){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            List<Product> products = category.getProducts();
            List<ProductDto> productDtos = new ArrayList<>();
            products.forEach(
                    product -> {
                        ProductDto productDto = new ProductDto();
                        productDto.setTitle(product.getTitle());
                        productDto.setDescription(product.getDescription());
                        productDto.setImage(product.getImage());
                        productDto.setPrice(product.getPrice());
                        productDtos.add(productDto);
                    }
            );
            categoryDto.setProducts(productDtos);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }
}

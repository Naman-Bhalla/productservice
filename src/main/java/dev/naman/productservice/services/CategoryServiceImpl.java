package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public CategoryDto convertCategoryToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    @Override
    public List<GenericProductDto> getProductsByACategory(String categoryName) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);

        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category Name not found in the database");
        }

        List<Product> products = categoryOptional.get().getProducts();
        List<GenericProductDto>  genericProductDtoList = new ArrayList<>();
        for (Product product: products
        ) {
            GenericProductDto genericProductDto = convertProductIntoGenericProduct(product);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    private GenericProductDto convertProductIntoGenericProduct(Product product ) {

        GenericProductDto genericProductDto  = new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setCategory(product.getCategory().getName());
        return genericProductDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (Category category: categories
             ) {
            categoryDtoList.add(convertCategoryToCategoryDto(category));
        }

        return categoryDtoList;
    }
}

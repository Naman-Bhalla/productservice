package dev.naman.productservice.services;

import dev.naman.productservice.convertor.Convertor;
import dev.naman.productservice.dtos.GenericCategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
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
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    Convertor convertor;
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository, Convertor convertor){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.convertor = convertor;
    }
    @Override
    public List<GenericCategoryDto> getAllCategories() throws NotFoundException {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new NotFoundException("No categories exist!");
        }
        List<GenericCategoryDto> genericCategories = new ArrayList<>();
        for(Category category: categories){
            genericCategories.add(convertor.convertCategoryToGenericCategory(category));
        }
        return genericCategories;
    }

    @Override
    public List<GenericProductDto> getProductByCategory(UUID uuid) throws NotFoundException {
        Optional<Category> fetchedCategory = categoryRepository.findById(uuid);
        if(fetchedCategory.isEmpty()){
            throw new NotFoundException("The category searched for does not exist!");
        }
        Category category = fetchedCategory.get();
        List<Product> products = productRepository.findAllByCategory_Name(category.getName());
        if(products.isEmpty()){
            throw new NotFoundException("There are no products under searched category");
        }
        List<GenericProductDto> genericProducts = new ArrayList<>();
        for(Product product: products){
            genericProducts.add(convertor.convertProductToGenericProductDto(product));
        }
        return genericProducts;
    }
}

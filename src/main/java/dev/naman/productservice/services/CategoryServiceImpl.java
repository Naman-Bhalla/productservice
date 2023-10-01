package dev.naman.productservice.services;

import dev.naman.productservice.dtos.convertor.ProductConvertor;
import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository ){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto getAllCategories() {
        CategoryDto categoryDto = new CategoryDto();
        List<String> categoryList = categoryRepository.findDistinctCategoriesByName();
        categoryDto.setCategories(categoryList);
        return  categoryDto;
    }


    @Override
    public List<GenericProductDto> getProductsByCategory(String name){
        Category category = categoryRepository.getCategoryByName(name);
//        Category category = new Category();
        List<Product> products = category.getProducts();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for(Product product : products){
            genericProductDtos.add(ProductConvertor.getGenericProductDto(product));
        }

        return genericProductDtos;
    }

}

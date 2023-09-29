package dev.bhanu.productservice.services;

import dev.bhanu.productservice.Repository.CategoryRepository;
import dev.bhanu.productservice.dtos.SelfCategoryDto;
import dev.bhanu.productservice.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DBCategoryService")
public class DBCategoryService implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public DBCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<SelfCategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.getAllCategory();

        List<SelfCategoryDto> dbCategories = new ArrayList<>();

        for(Category category: categories){
            dbCategories.add(convertCategoryToDBCategory(category));
        }

        return  dbCategories;
    }

    SelfCategoryDto convertCategoryToDBCategory(Category category){
        SelfCategoryDto selfCategoryDto = new SelfCategoryDto();
        selfCategoryDto.setName(category.getName());
        selfCategoryDto.setId(category.getId());
        return selfCategoryDto;
    }
}

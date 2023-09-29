package dev.bhanu.productservice.controllers;

import dev.bhanu.productservice.services.DBCategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.bhanu.productservice.dtos.SelfCategoryDto;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private DBCategoryService dbCategoryService;

    public  CategoryController(@Qualifier("DBCategoryService") DBCategoryService dbCategoryService){
        this.dbCategoryService = dbCategoryService;
    }

    @GetMapping
    public List<SelfCategoryDto> getAllCategory(){
        return dbCategoryService.getAllCategory();
    }
}

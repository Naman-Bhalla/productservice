package dev.naman.productservice.controller;

import dev.pranay.productservice.dtos.ProductDto;
import dev.pranay.productservice.models.Product;
import dev.pranay.productservice.services.CategoryServiceDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryServiceDB categoryServiceDB;

    public CategoryController(CategoryServiceDB categoryServiceDB) {
        this.categoryServiceDB = categoryServiceDB;
    }


    @GetMapping("/{uuid}")
    public List<ProductDto> getCategory(@PathVariable("uuid") String uuid) {
        List<Product> products = categoryServiceDB.getCategoryById(uuid).getProducts();

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product: products) {
            ProductDto productDto = new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setTitle(product.getTitle());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            productDtos.add(productDto);
        }

        return productDtos;
    }

    // get all categories
    @GetMapping("/all")
    public List<String> getAllCategories(){

        return categoryServiceDB.getAllCategories();
    }
}

package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.GetProductTitlesRequestDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{uuid}")
    public List<GenericProductDto> getAllProductsFromACategory(@PathVariable("uuid") String uuid) throws NotFoundException {

        List<Product> products = categoryService.getAllProductsFromACategory(uuid).getProducts();
        List<GenericProductDto> productDtos = new ArrayList<>();

        for (Product product: products) {
            GenericProductDto productDto = new GenericProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setUuid(product.getUuid());
            productDto.setTitle(product.getTitle());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice().getPrice());
            productDto.setCurrency(product.getCurrency());
            productDto.setCategory(product.getCategory().getName());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @GetMapping("/")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    //    @GetMapping("/titles/")
//    public List<String> getProductTitles(@RequestBody GetProductTitlesRequestDto requestDto) {
//
//        System.out.println("getProductTitles() called");
//        List<String> uuids = requestDto.getUuids();
//
//        return categoryService.getProductTitles(uuids);
//    }

}

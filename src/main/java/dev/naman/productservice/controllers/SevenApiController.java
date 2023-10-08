package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.services.CategoryService;
import dev.naman.productservice.services.SevenApiService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sevenapi")
public class SevenApiController {
    private SevenApiService sevenApiService;
    private CategoryService categoryService;

    public SevenApiController(SevenApiService sevenApiService,
                              CategoryService categoryService) {
        this.sevenApiService = sevenApiService;
        this.categoryService = categoryService;
    }

    //working
    @GetMapping("/product/{uuid}")
    public ProductDto getProductById(@PathVariable("uuid") String uuid) throws NotFoundException {
        ProductDto productDto = new ProductDto();
        Product product = sevenApiService.getProductById(uuid);
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());
        return productDto;
    }
    //working
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        List<Product> products = sevenApiService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products){
            ProductDto productDto = new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            productDto.setTitle(product.getTitle());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    //working
    @GetMapping("/categories/{uuid}")
    public List<ProductDto> getProductsByCategory(@PathVariable("uuid") String uuid) {
        List<Product> products = sevenApiService.getAllProductsByCategory(uuid).getProducts();
        List<ProductDto> allproducts = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            productDto.setTitle(product.getTitle());
            allproducts.add(productDto);
        }

        return allproducts;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return sevenApiService.getAllCategories();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return sevenApiService.addProduct(product);
    }

    @PutMapping("/{uuid}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("uuid") String uuid) {
        return sevenApiService.updateProduct(product, uuid);
    }

    @DeleteMapping("/{uuid}")
    public String deleteProduct(@PathVariable("uuid") String uuid) {
        return sevenApiService.deleteProduct(uuid);
    }
}

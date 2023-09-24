package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Qualifier("selfProductServiceImpl")
    private ProductService productService;

    public ProductController(ProductService productService){;
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") String id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id") UUID id){
        productService.deleteProductById(id);
    }

    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") UUID id, @RequestBody Product product){
        productService.updateProductById(id, product);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

}

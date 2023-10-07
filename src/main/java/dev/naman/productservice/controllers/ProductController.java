package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {


    // Field Injection
    //    @Autowired
    private final ProductService productService;

    // Setter Injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }


    // use code for LOCAL DATABASE
    // *****************************************************************************************************************
    //     Constructor Injection
    @Autowired
    public ProductController(@Qualifier("selfProductServiceImpl") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {

        return productService.createProduct(product);
    }

    @GetMapping("/")
    public List<GenericProductDto> getAllProducts() throws NotFoundException {

        if (productService.getAllProducts().isEmpty()) {
            System.out.println("No products found in the database.");
            return new ArrayList<>();
        }
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID uuid) throws NotFoundException {

        System.out.println("********************");
        // Access the qualified bean
        String qualifierValue = productService.getQualifierValue();
        System.out.println("Qualifier value: " + qualifierValue);
        System.out.println("********************");
        return productService.getProductById(uuid);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@PathVariable("id") UUID uuid, @RequestBody GenericProductDto genericProductDto) throws NotFoundException {

        return productService.updateProduct(uuid, genericProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID uuid) throws NotFoundException {

        return new ResponseEntity<>(productService.deleteProduct(uuid), HttpStatus.OK);
    }

    // *****************************************************************************************************************


    // use code for FAKESTORE API
    // *****************************************************************************************************************

//    // Constructor Injection
//    @Autowired
//    public ProductController(@Qualifier("fakeStoreProductServiceImpl") ProductService productService) {
//        this.productService = productService;
//    }
//
//    @PostMapping("/")
//    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
//
//        return productService.createProduct(genericProductDto);
//    }
//
//    @GetMapping("/")
//    public List<GenericProductDto> getAllProducts() throws NotFoundException {
//
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/{id}")
//    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
//
//        return productService.getProductById(id);
//    }
//
//    @PutMapping("/{id}")
//    public GenericProductDto updateProduct(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws NotFoundException {
//
//        return productService.updateProduct(id, genericProductDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public GenericProductDto deleteProduct(@PathVariable("id") Long id) throws NotFoundException {
//
//        return productService.deleteProduct(id);
//    }
    // *****************************************************************************************************************


}

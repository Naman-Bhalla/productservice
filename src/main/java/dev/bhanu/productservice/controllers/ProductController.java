package dev.bhanu.productservice.controllers;


import dev.bhanu.productservice.Exception.NotFoundException;
import dev.bhanu.productservice.dtos.ExceptionDto;
import dev.bhanu.productservice.dtos.GenericProductDto;
import dev.bhanu.productservice.services.DbProductService;
import dev.bhanu.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private DbProductService dbProductService;

    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService")  ProductService productService, DbProductService dbProductService){
        this.productService = productService;
        this.dbProductService = dbProductService;
    }

    @GetMapping("{id}")
    public GenericProductDto getProductByid(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
//        return dbProductService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto product){
        return productService.updateProductById(id, product);

    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProduct();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity(new ExceptionDto(notFoundException.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

}

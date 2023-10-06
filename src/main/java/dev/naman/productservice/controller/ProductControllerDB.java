package dev.naman.productservice.controller;

import dev.pranay.productservice.dtos.GenericProductDtoDB;
import dev.pranay.productservice.exception.NotFoundException;
import dev.pranay.productservice.services.ProductServiceDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dbproducts")
public class ProductControllerDB {
    private ProductServiceDB productServiceDB;

    public ProductControllerDB(ProductServiceDB productServiceDB){
        this.productServiceDB = productServiceDB;
    }



    //GET /products {}
    @GetMapping
    public List<GenericProductDtoDB> getAllProducts() {
        return productServiceDB.getAllProducts();
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDtoDB getProductById(@PathVariable("id") String id) throws NotFoundException {
        return productServiceDB.getProductById(UUID.fromString(id));

    }


    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDtoDB> deleteProductById(@PathVariable("id") String id) throws NotFoundException {
        return new ResponseEntity<>(
                productServiceDB.deleteProductById(UUID.fromString(id)),
                HttpStatus.OK
        );

    }

    @PostMapping
    public GenericProductDtoDB createProduct(@RequestBody GenericProductDtoDB product) {
        return productServiceDB.createProduct(product);

    }

    @PutMapping("{id}")
    public GenericProductDtoDB updateProductById(@RequestBody GenericProductDtoDB product, @PathVariable("id")String id) {
        return productServiceDB.updateProductById(product, UUID.fromString(id));
    }
}

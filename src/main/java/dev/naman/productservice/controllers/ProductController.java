package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.ExceptionDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    // field injection
    private ProductService productService;
    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // #1 - Get All Products
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    // #2 - Get a single Product
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") String id) throws NotFoundException {
        return productService.getProductById(id);
    }

    // #7 - Delete a product
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") String id) throws NotFoundException {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    // #5 - Add new Product
    @PostMapping
    public String createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productService.createProduct(product);
    }

    // #6 - Update a product
    @PutMapping("{id}")
    public void updateProductById(@RequestBody GenericProductDto product) {

    }
}

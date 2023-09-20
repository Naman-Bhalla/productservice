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
import java.util.UUID;

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

    // GET /products {}
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/uuid/{uuid}")
    public GenericProductDto getProductByUUID(@PathVariable("uuid")UUID uuid) throws NotFoundException{
        return productService.getProductById(uuid);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<GenericProductDto> deleteProductByUUID(@PathVariable("uuid") UUID uuid){
        return new ResponseEntity<GenericProductDto>(
                productService.deleteProduct(uuid),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById() {

    }
}
